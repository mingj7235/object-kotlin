package com.study.objects.reservation.service

import com.study.objects.reservation.domain.DiscountCondition
import com.study.objects.reservation.domain.Reservation
import com.study.objects.reservation.domain.Screening
import com.study.objects.reservation.persistence.DiscountConditionRepository
import com.study.objects.reservation.persistence.DiscountPolicyRepository
import com.study.objects.reservation.persistence.MovieRepository
import com.study.objects.reservation.persistence.ReservationRepository
import com.study.objects.reservation.persistence.ScreeningRepository

class ReservationService(
    private val discountConditionRepository: DiscountConditionRepository,
    private val discountPolicyRepository: DiscountPolicyRepository,
    private val movieRepository: MovieRepository,
    private val reservationRepository: ReservationRepository,
    private val screeningRepository: ScreeningRepository,
) {
    fun reserveScreening(
        customerId: Long,
        screeningId: Long,
        audienceCount: Int,
    ): Reservation {
        val screening = screeningRepository.selectScreening(screeningId) ?: throw IllegalArgumentException("Screening not found")
        val movie = movieRepository.selectMovie(screening.movieId) ?: throw IllegalArgumentException("Movie not found")
        val discountPolicy =
            discountPolicyRepository.selectDiscountPolicy(movie.id!!) ?: throw IllegalArgumentException("Discount policy not found")
        val discountConditions = discountConditionRepository.selectDiscountConditions(discountPolicy.id!!)

        val condition =
            findDiscountCondition(
                discountConditions = discountConditions,
                screening = screening,
            )

        val fee =
            if (condition != null) {
                movie.fee.minus(discountPolicy.calcuateDiscount(movie))
            } else {
                movie.fee
            }

        val reservation =
            Reservation(
                customerId = customerId,
                screeningId = screeningId,
                fee = fee,
                audienceCount = audienceCount,
            )
        reservationRepository.insert(reservation)
        return reservation
    }

    private fun findDiscountCondition(
        discountConditions: List<DiscountCondition>,
        screening: Screening,
    ): DiscountCondition? {
        discountConditions.forEach { condition ->
            if (condition.isSatisfiedBy(screening)) return condition
        }
        return null
    }
}

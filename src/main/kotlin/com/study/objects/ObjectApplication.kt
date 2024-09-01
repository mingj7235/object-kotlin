package com.study.objects

import com.study.objects.generic.Money
import com.study.objects.reservation.domain.DiscountCondition
import com.study.objects.reservation.domain.DiscountPolicy
import com.study.objects.reservation.domain.Movie
import com.study.objects.reservation.domain.Screening
import com.study.objects.reservation.persistence.DiscountConditionRepository
import com.study.objects.reservation.persistence.DiscountPolicyRepository
import com.study.objects.reservation.persistence.MovieRepository
import com.study.objects.reservation.persistence.ReservationRepository
import com.study.objects.reservation.persistence.ScreeningRepository
import com.study.objects.reservation.persistence.memory.DiscountConditionMemoryRepository
import com.study.objects.reservation.persistence.memory.DiscountPolicyMemoryRepository
import com.study.objects.reservation.persistence.memory.MovieMemoryRepository
import com.study.objects.reservation.persistence.memory.ReservationMemoryRepository
import com.study.objects.reservation.persistence.memory.ScreeningMemoryRepository
import com.study.objects.reservation.service.ReservationService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.LocalDateTime
import java.time.LocalTime.of

@SpringBootApplication
class ObjectApplication

class MainRunner {
    private val screeningRepository: ScreeningRepository = ScreeningMemoryRepository()
    private val movieRepository: MovieRepository = MovieMemoryRepository()
    private val discountPolicyRepository: DiscountPolicyRepository = DiscountPolicyMemoryRepository()
    private val discountConditionRepository: DiscountConditionRepository = DiscountConditionMemoryRepository()
    private val reservationRepository: ReservationRepository = ReservationMemoryRepository()
    private val reservationService =
        ReservationService(
            screeningRepository = screeningRepository,
            movieRepository = movieRepository,
            discountPolicyRepository = discountPolicyRepository,
            discountConditionRepository = discountConditionRepository,
            reservationRepository = reservationRepository,
        )

    fun run() {
        val screening = initializeData()
        val reservation =
            reservationService.reserveScreening(
                1L,
                screening.id!!,
                2,
            )
        println("관객수 : ${reservation.audienceCount}, 요금: ${reservation.fee.formattedValue}")
    }

    private fun initializeData(): Screening {
        val movie =
            Movie(
                title = "한산",
                runningTime = 150,
                fee = Money.wons(10000),
            ).also { movieRepository.insert(it) }

        val discountPolicy =
            DiscountPolicy(
                movieId = movie.id!!,
                policyType = DiscountPolicy.PolicyType.AMOUNT_POLICY,
                amount = Money.wons(1000),
                percent = null,
            ).also { discountPolicyRepository.insert(it) }

        listOf(
            DiscountCondition(
                policyId = discountPolicy.id!!,
                conditionType = DiscountCondition.ConditionType.SEQUENCE_CONDITION,
                dayOfWeek = null,
                startTime = null,
                endTime = null,
                sequence = 1,
            ),
            DiscountCondition(
                policyId = discountPolicy.id,
                conditionType = DiscountCondition.ConditionType.SEQUENCE_CONDITION,
                dayOfWeek = null,
                startTime = null,
                endTime = null,
                sequence = 10,
            ),
            DiscountCondition(
                policyId = discountPolicy.id,
                conditionType = DiscountCondition.ConditionType.PERIOD_CONDITION,
                dayOfWeek = MONDAY,
                startTime = of(10, 0),
                endTime = of(12, 0),
                sequence = null,
            ),
            DiscountCondition(
                policyId = discountPolicy.id,
                conditionType = DiscountCondition.ConditionType.PERIOD_CONDITION,
                dayOfWeek = WEDNESDAY,
                startTime = of(18, 0),
                endTime = of(21, 0),
                sequence = null,
            ),
        ).forEach { discountConditionRepository.insert(it) }

        return Screening(
            movieId = movie.id,
            sequence = 7,
            screeningTime = LocalDateTime.of(2024, 12, 11, 18, 0),
        ).also {
            screeningRepository.insert(it)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ObjectApplication>(*args)
    val runner = MainRunner()
    runner.run()
}

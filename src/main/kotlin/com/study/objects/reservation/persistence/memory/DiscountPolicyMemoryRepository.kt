package com.study.objects.reservation.persistence.memory

import com.study.objects.reservation.domain.DiscountPolicy
import com.study.objects.reservation.persistence.DiscountPolicyRepository

class DiscountPolicyMemoryRepository :
    InMemoryRepository<DiscountPolicy>(),
    DiscountPolicyRepository {
    override fun selectDiscountPolicy(movieId: Long): DiscountPolicy? = findOne { it.movieId == movieId }
}

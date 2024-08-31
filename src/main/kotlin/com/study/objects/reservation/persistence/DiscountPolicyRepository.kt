package com.study.objects.reservation.persistence

import com.study.objects.reservation.domain.DiscountPolicy

interface DiscountPolicyRepository {
    fun selectDiscountPolicy(movieId: Long): DiscountPolicy?

    fun insert(discountPolicy: DiscountPolicy)
}

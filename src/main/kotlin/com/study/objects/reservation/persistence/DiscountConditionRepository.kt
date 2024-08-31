package com.study.objects.reservation.persistence

import com.study.objects.reservation.domain.DiscountCondition

interface DiscountConditionRepository {
    fun selectDiscountConditions(policyId: Long): List<DiscountCondition>

    fun insert(discountCondition: DiscountCondition)
}

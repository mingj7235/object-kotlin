package com.study.objects.reservation.persistence.memory

import com.study.objects.reservation.domain.DiscountCondition
import com.study.objects.reservation.persistence.DiscountConditionRepository

class DiscountConditionMemoryRepository :
    InMemoryRepository<DiscountCondition>(),
    DiscountConditionRepository {
    override fun selectDiscountConditions(policyId: Long): List<DiscountCondition> = findMany { it.policyId == policyId }
}

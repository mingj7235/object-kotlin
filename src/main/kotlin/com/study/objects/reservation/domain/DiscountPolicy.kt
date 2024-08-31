package com.study.objects.reservation.domain

import com.study.objects.generic.Money

class DiscountPolicy(
    val id: Long? = null,
    val movieId: Long,
    val policyType: PolicyType,
    val amount: Money? = null,
    val percent: Double? = null,
) {
    enum class PolicyType {
        PERCENT_POLICY,
        AMOUNT_POLICY,
    }

    val isAmountPolicy: Boolean
        get() = policyType == PolicyType.AMOUNT_POLICY

    val isPercentPolicy: Boolean
        get() = policyType == PolicyType.PERCENT_POLICY
}

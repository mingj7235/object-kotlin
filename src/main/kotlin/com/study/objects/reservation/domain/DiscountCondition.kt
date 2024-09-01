package com.study.objects.reservation.domain

import java.time.DayOfWeek

class DiscountCondition(
    val id: Long? = null,
    val policyId: Long,
    val conditionType: ConditionType,
    val dayOfWeek: DayOfWeek? = null,
    val interval: TimeInterval? = null,
    val sequence: Int? = null,
) {
    enum class ConditionType {
        PERIOD_CONDITION,
        SEQUENCE_CONDITION,
        COMBINED_CONDITION,
    }

    val isPeriodCondition: Boolean
        get() = conditionType == ConditionType.PERIOD_CONDITION

    val isSequenceCondition: Boolean
        get() = conditionType == ConditionType.SEQUENCE_CONDITION

    val isCombinedCondition: Boolean
        get() = conditionType == ConditionType.COMBINED_CONDITION
}

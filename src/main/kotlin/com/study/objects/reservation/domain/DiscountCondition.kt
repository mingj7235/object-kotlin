package com.study.objects.reservation.domain

import java.time.DayOfWeek
import java.time.LocalTime

class DiscountCondition(
    val id: Long? = null,
    val policyId: Long,
    val conditionType: ConditionType,
    val dayOfWeek: DayOfWeek? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val sequence: Int? = null,
) {
    enum class ConditionType {
        PERIOD_CONDITION,
        SEQUENCE_CONDITION,
    }

    val isPeriodCondition: Boolean
        get() = conditionType == ConditionType.PERIOD_CONDITION

    val isSequenceCondition: Boolean
        get() = conditionType == ConditionType.SEQUENCE_CONDITION
}

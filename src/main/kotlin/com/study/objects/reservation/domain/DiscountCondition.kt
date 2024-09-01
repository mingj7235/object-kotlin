package com.study.objects.reservation.domain

import com.study.objects.generic.TimeInterval
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

    private val isPeriodCondition: Boolean
        get() = conditionType == ConditionType.PERIOD_CONDITION

    private val isSequenceCondition: Boolean
        get() = conditionType == ConditionType.SEQUENCE_CONDITION

    private val isCombinedCondition: Boolean
        get() = conditionType == ConditionType.COMBINED_CONDITION

    fun isSatisfiedBy(screening: Screening): Boolean {
        if (isPeriodCondition) {
            if (screening.isPlayedIn(
                    dayOfWeek = dayOfWeek!!,
                    startTime = interval!!.startTime,
                    endTime = interval.endTime,
                )
            ) {
                return true
            }
        }

        if (isSequenceCondition) {
            if (sequence == screening.sequence) {
                return true
            }
        }

        if (isCombinedCondition) {
            if (sequence == screening.sequence &&
                screening.isPlayedIn(
                    dayOfWeek = dayOfWeek!!,
                    startTime = interval!!.startTime,
                    endTime = interval.endTime,
                )
            ) {
                return true
            }
        }

        return false
    }
}

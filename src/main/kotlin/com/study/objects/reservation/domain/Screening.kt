package com.study.objects.reservation.domain

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class Screening(
    val id: Long? = null,
    val movieId: Long,
    val sequence: Int,
    val screeningTime: LocalDateTime,
) {
    fun isPlayedIn(
        dayOfWeek: DayOfWeek,
        startTime: LocalTime,
        endTime: LocalTime,
    ): Boolean =
        screeningTime.dayOfWeek == dayOfWeek &&
            screeningTime.toLocalTime().let { time ->
                time >= startTime && time < endTime
            }
}

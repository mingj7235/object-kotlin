package com.study.objects.reservation.domain

import java.time.LocalTime

data class TimeInterval(
    val startTime: LocalTime,
    val endTime: LocalTime,
)

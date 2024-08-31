package com.study.objects.reservation.domain

import com.study.objects.generic.Money

data class Movie(
    val id: Long? = null,
    val title: String,
    val runningTime: Int,
    val fee: Money,
)

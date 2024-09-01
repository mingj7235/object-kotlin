package com.study.objects.reservation.domain

import com.study.objects.generic.Money

class Movie(
    val id: Long? = null,
    val title: String,
    val runningTime: Int,
    val fee: Money,
)

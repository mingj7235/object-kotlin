package com.study.objects.reservation.domain

import com.study.objects.generic.Money

data class Reservation(
    val id: Long? = null,
    val customerId: Long,
    val screeningId: Long,
    val audienceCount: Int,
    val fee: Money,
)

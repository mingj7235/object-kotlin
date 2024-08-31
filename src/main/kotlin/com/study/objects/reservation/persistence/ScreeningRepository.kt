package com.study.objects.reservation.persistence

import com.study.objects.reservation.domain.Screening

interface ScreeningRepository {
    fun selectScreening(screeningId: Long): Screening?

    fun insert(screening: Screening)
}

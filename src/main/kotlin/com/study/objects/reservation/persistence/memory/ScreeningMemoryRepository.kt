package com.study.objects.reservation.persistence.memory

import com.study.objects.reservation.domain.Screening
import com.study.objects.reservation.persistence.ScreeningRepository

class ScreeningMemoryRepository :
    InMemoryRepository<Screening>(),
    ScreeningRepository {
    override fun selectScreening(screeningId: Long): Screening? = findOne { it.id == screeningId }
}

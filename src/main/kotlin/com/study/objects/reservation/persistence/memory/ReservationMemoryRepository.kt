package com.study.objects.reservation.persistence.memory

import com.study.objects.reservation.domain.Reservation
import com.study.objects.reservation.persistence.ReservationRepository

class ReservationMemoryRepository :
    InMemoryRepository<Reservation>(),
    ReservationRepository

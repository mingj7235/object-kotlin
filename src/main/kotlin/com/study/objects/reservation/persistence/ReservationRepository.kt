package com.study.objects.reservation.persistence

import com.study.objects.reservation.domain.Reservation

interface ReservationRepository {
    fun insert(reservation: Reservation)
}

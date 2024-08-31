package com.study.objects.reservation.persistence

import com.study.objects.reservation.domain.Movie

interface MovieRepository {
    fun selectMovie(movieId: Long): Movie?

    fun insert(movie: Movie)
}

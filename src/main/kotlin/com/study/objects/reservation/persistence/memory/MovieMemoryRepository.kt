package com.study.objects.reservation.persistence.memory

import com.study.objects.reservation.domain.Movie
import com.study.objects.reservation.persistence.MovieRepository

class MovieMemoryRepository :
    InMemoryRepository<Movie>(),
    MovieRepository {
    override fun selectMovie(movieId: Long): Movie? = findOne { it.id == movieId }
}

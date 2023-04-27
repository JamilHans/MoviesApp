package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetUpcomingMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getUpcomingMovies() = moviesRepository.getUpcomingMovies()

}
package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetPopulateMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getPopulateMovies() = moviesRepository.getPopulateMovies()
}

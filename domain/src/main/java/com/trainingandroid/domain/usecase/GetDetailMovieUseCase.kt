package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetDetailMovieUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getDetailMovie(id: Int) = moviesRepository.getDetailMovie(id)
}

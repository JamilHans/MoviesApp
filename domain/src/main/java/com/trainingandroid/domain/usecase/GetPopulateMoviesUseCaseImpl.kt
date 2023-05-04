package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetPopulateMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetPopulateMoviesUseCase {
    override suspend fun getPopulateMovies() = moviesRepository.getPopulateMovies()
}

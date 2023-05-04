package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetPopulateMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetPopulateMoviesUseCase {
    override suspend operator fun invoke() = moviesRepository.getPopulateMovies()
}

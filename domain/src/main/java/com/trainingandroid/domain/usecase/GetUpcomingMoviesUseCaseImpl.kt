package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetUpcomingMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetUpcomingMoviesUseCase {

    override suspend operator fun invoke() = moviesRepository.getUpcomingMovies()

}
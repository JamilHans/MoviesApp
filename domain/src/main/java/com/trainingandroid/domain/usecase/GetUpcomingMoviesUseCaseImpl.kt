package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository

class GetUpcomingMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetUpcomingMoviesUseCase {
    override suspend fun invoke(page: Int) = moviesRepository.getUpcomingMovies(page)

}

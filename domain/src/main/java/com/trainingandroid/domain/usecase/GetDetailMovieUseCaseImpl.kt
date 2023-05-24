package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MoviesRepository


class GetDetailMovieUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetDetailMovieUseCase {

    override suspend operator fun invoke(id: Int) = moviesRepository.getDetailMovie(id)
}

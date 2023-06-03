package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.repositories.MovieDetailRepository


class GetDetailMovieUseCaseImpl(
    private val movieDetailRepository: MovieDetailRepository
) : GetDetailMovieUseCase {
    override suspend operator fun invoke(id: Int) = movieDetailRepository.getDetailMovie(id)
}


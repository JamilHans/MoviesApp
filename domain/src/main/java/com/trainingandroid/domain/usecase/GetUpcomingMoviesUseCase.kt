package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.movie.Movies

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(): Result<List<Movies>>
}
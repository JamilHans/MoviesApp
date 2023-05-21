package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.movie.Movies

interface GetPopulateMoviesUseCase {
    suspend operator fun invoke(): ResultType<List<Movies>>
}

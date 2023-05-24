package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(): ResultType<List<Movies>, Error>
}

package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.MovieList
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(page: Int): ResultType<MovieList<Movies>, Error>
}

package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.movie.Movies

interface GetPopulateMoviesUseCase {
    suspend fun getPopulateMovies(): Result<List<Movies>>
}
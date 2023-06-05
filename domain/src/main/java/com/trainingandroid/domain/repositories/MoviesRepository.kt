package com.trainingandroid.domain.repositories

import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType

interface MoviesRepository {
    suspend fun getUpcomingMovies(): ResultType<List<Movies>, Error>
    suspend fun getPopulateMovies(): ResultType<List<Movies>, Error>
}


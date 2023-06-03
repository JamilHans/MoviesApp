package com.trainingandroid.data.datasource

import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

interface MoviesRemoteDataSource {
    suspend fun getUpcomingMovies(): ResultType<List<MovieResponse>, Error>
    suspend fun getPopulateMovies(): ResultType<List<MovieResponse>, Error>
}

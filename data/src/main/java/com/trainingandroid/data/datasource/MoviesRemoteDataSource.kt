package com.trainingandroid.data.datasource

import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

interface MoviesRemoteDataSource {
    suspend fun getUpcomingMovies(page: Int): ResultType<MovieListResponse<MovieResponse>, Error>
    suspend fun getPopulateMovies(page: Int): ResultType<MovieListResponse<MovieResponse>, Error>
}

package com.trainingandroid.data.datasource

import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType

interface MoviesRemoteDataSource {

    suspend fun getUpcomingMovies(): ResultType<List<Movies>>
    suspend fun getPopulateMovies(): ResultType<List<Movies>>
    suspend fun getDetailMovie(id: Int): ResultType<DetailMovie>

}

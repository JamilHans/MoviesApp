package com.trainingandroid.mobiedbapp.data.datasource

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.movie.Movies

interface MoviesRemoteDataSource {

    suspend fun getUpcomingMovies(): Result<List<Movies>>
    suspend fun getPopulateMovies(): Result<List<Movies>>
    suspend fun getDetailMovie(id: Int): Result<DetailMovie>

}
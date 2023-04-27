package com.trainingandroid.mobiedbapp.data.datasource

import com.trainingandroid.mobiedbapp.data.model.movie.Result
import com.trainingandroid.mobiedbapp.domain.model.Movies
import com.trainingandroid.mobiedbapp.domain.model.detail.DetailMovie

interface MoviesRemoteDataSource {

    suspend fun getUpcomingMovies(): Result<List<Movies>>
    suspend fun getPopulateMovies(): Result<List<Movies>>
    suspend fun getDetailMovie(id: Int): Result<DetailMovie>

}
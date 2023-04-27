package com.trainingandroid.mobiedbapp.data.repositories

import com.trainingandroid.mobiedbapp.data.datasource.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {
    suspend fun getUpcomingMovies() = moviesRemoteDataSource.getUpcomingMovies()
    suspend fun getPopulateMovies() = moviesRemoteDataSource.getPopulateMovies()
    suspend fun getDetailMovie(id: Int) = moviesRemoteDataSource.getDetailMovie(id)
}
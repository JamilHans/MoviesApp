package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.domain.repositories.MoviesRepository

class MoviesRepositoryImpl constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getUpcomingMovies() = moviesRemoteDataSource.getUpcomingMovies()
    override suspend fun getPopulateMovies() = moviesRemoteDataSource.getPopulateMovies()
    override suspend fun getDetailMovie(id: Int) = moviesRemoteDataSource.getDetailMovie(id)
}
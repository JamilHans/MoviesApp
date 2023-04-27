package com.trainingandroid.mobiedbapp.data.repositories

import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.mobiedbapp.data.datasource.MoviesRemoteDataSource

class MoviesRepositoryImpl constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getUpcomingMovies() = moviesRemoteDataSource.getUpcomingMovies()
    override suspend fun getPopulateMovies() = moviesRemoteDataSource.getPopulateMovies()
    override suspend fun getDetailMovie(id: Int) = moviesRemoteDataSource.getDetailMovie(id)
}
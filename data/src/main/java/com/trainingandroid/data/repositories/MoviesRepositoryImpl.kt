package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.domain.resource.ResultType


class MoviesRepositoryImpl constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getUpcomingMovies(): ResultType<List<Movies>, Error> {
        return when (val result = moviesRemoteDataSource.getUpcomingMovies()) {
            is ResultType.Error -> {
                ResultType.Error(result.value)
            }
            is ResultType.Success -> {
                ResultType.Success(result.value.map {
                    it.toDomainModel()
                })
            }
        }
    }

    override suspend fun getPopulateMovies(): ResultType<List<Movies>, Error> {
        return when (val result = moviesRemoteDataSource.getPopulateMovies()) {
            is ResultType.Error -> {
                ResultType.Error(result.value)
            }
            is ResultType.Success -> {
                ResultType.Success(result.value.map {
                    it.toDomainModel()
                })
            }
        }
    }

}

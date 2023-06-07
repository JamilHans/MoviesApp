package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.MovieList
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.domain.resource.ResultType

class MoviesRepositoryImpl constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getUpcomingMovies(page: Int): ResultType<MovieList<Movies>, Error> {
        return when (val result = moviesRemoteDataSource.getUpcomingMovies(page)) {
            is ResultType.Error -> {
                ResultType.Error(result.value)
            }
            is ResultType.Success -> {
                ResultType.Success(result.value.toDomainModel())
            }
        }
    }

    override suspend fun getPopulateMovies(page: Int): ResultType<MovieList<Movies>, Error> {
        return when (val result = moviesRemoteDataSource.getPopulateMovies(page)) {
            is ResultType.Error -> {
                ResultType.Error(result.value)
            }
            is ResultType.Success -> {
                ResultType.Success(result.value.toDomainModel())
            }
        }
    }

}

package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MovieDetailDataSource
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.repositories.MovieDetailRepository
import com.trainingandroid.domain.resource.ResultType

class MovieDetailRepositoryImpl constructor(
    private val movieDetailDataSource: MovieDetailDataSource
): MovieDetailRepository {
    override suspend fun getDetailMovie(id: Int): ResultType<DetailMovie, Error> {
        return when (val result = movieDetailDataSource.getDetailMovie(id)) {
            is ResultType.Error -> {
                ResultType.Error(result.value)
            }
            is ResultType.Success -> {
                ResultType.Success(result.value.toDomainModel())
            }
        }
    }
}

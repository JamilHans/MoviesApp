package com.trainingandroid.data.datasource

import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

interface MovieDetailRemoteDataSource {
    suspend fun getDetailMovie(id: Int): ResultType<MovieDetailResponse, Error>
}

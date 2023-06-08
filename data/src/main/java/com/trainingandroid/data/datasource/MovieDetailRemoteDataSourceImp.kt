package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.data.util.toResultType
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

class MovieDetailRemoteDataSourceImp(private val remoteService: RemoteService) :
    MovieDetailRemoteDataSource {
    override suspend fun getDetailMovie(id: Int): ResultType<MovieDetailResponse, Error> {
        val response = remoteService.getMovieDetail(id)
        return response.toResultType()
    }
}

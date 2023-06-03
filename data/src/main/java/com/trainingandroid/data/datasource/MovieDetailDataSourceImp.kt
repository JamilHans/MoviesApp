package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.data.util.convertNetworkResponseToResultType
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

class MovieDetailDataSourceImp(private val remoteService: RemoteService) : MovieDetailDataSource {
    override suspend fun getDetailMovie(id: Int): ResultType<MovieDetailResponse, Error> {
        val response = remoteService.getMovieDetail(id)
        return convertNetworkResponseToResultType(response)
    }
}

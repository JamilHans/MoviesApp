package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.util.convertToResultType
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

class MoviesRemoteDataSourceImp(private val remoteService: RemoteService) :
    MoviesRemoteDataSource {

    override suspend fun getUpcomingMovies(): ResultType<List<MovieResponse>, Error> {
        val response = remoteService.getUpcoming()
        return response.convertToResultType()
    }

    override suspend fun getPopulateMovies(): ResultType<List<MovieResponse>, Error> {
        val response = remoteService.getPopular()
        return response.convertToResultType()
    }
}









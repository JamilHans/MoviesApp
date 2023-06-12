package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.util.toResultType
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

class MoviesRemoteDataSourceImp(private val remoteService: RemoteService) :
    MoviesRemoteDataSource {
    override suspend fun getUpcomingMovies(page: Int):
            ResultType<MovieListResponse<MovieResponse>, Error> {
        val response = remoteService.getUpcoming(page = page)
        return response.toResultType()
    }

    override suspend fun getPopulateMovies(page: Int):
            ResultType<MovieListResponse<MovieResponse>, Error> {
        val response = remoteService.getPopular(page = page)
        return response.toResultType()
    }
}




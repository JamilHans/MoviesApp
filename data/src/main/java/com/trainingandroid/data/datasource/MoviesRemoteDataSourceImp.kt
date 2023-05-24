package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

class MoviesRemoteDataSourceImp(private val remoteService: RemoteService) :
    MoviesRemoteDataSource {

    override suspend fun getUpcomingMovies(): ResultType<List<MovieResponse>, Error> {
        return when (val response = remoteService.getUpcoming()) {
            is NetworkResponse.ApiError -> {
                ResultType.Error(Error(message = "Encontramos un error en la solicitud"))
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(Error(message = "No se pudo conectar al servidor, revise su conexion a internet"))
            }
            is NetworkResponse.Success -> {
                ResultType.Success(response.body.results!!)
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(Error(message = response.error.message.orEmpty()))
            }
        }
    }

    override suspend fun getPopulateMovies(): ResultType<List<MovieResponse>, Error> {
        return when (val response = remoteService.getPopular()) {
            is NetworkResponse.ApiError -> {
                ResultType.Error(Error(message = "Encontramos un error en la solicitud"))
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(Error(message = "No se pudo conectar al servidor, revise su conexion a internet"))
            }
            is NetworkResponse.Success -> {
                ResultType.Success(response.body.results!!)
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(Error(message = response.error.message.orEmpty()))
            }
        }
    }

    override suspend fun getDetailMovie(id: Int): ResultType<MovieDetailResponse, Error> {
        return when (val response = remoteService.getMovieDetail(id)) {
            is NetworkResponse.ApiError -> {
                ResultType.Error(Error(message = "Encontramos un error en la solicitud"))
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(Error(message = "No se pudo conectar al servidor, revise su conexion a internet"))
            }
            is NetworkResponse.Success -> {
                ResultType.Success(response.body)
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(Error(message = response.error.message.orEmpty()))
            }
        }
    }
}







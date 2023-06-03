package com.trainingandroid.data.util

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.movie.WrappedListResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

fun convertNetworkResponseToResultType(response: NetworkResponse<WrappedListResponse<MovieResponse>, Error>):
        ResultType<List<MovieResponse>, Error> {
    return when (response) {
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

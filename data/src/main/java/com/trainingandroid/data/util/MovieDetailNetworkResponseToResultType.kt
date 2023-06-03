package com.trainingandroid.data.util

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

fun convertNetworkResponseToResultType(response: NetworkResponse<MovieDetailResponse, Error>):
        ResultType<MovieDetailResponse, Error> {
    return when (response) {
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

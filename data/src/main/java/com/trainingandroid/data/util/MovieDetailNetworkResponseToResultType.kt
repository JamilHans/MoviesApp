package com.trainingandroid.data.util

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

fun NetworkResponse<MovieDetailResponse, Error>.convertToResultType()
        : ResultType<MovieDetailResponse, Error> {
    return when (this) {
        is NetworkResponse.ApiError -> {
            ResultType.Error(Error(message = "Encontramos un error en la solicitud"))
        }
        is NetworkResponse.NetworkError -> {
            ResultType.Error(Error(message = "No se pudo conectar al servidor, revise su conexión a internet"))
        }
        is NetworkResponse.Success -> {
            ResultType.Success(body)
        }
        is NetworkResponse.UnknownError -> {
            ResultType.Error(Error(message = error.message.orEmpty()))
        }
    }
}






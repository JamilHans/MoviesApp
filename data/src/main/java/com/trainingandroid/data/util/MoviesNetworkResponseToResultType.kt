package com.trainingandroid.data.util

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.movie.WrappedListResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

fun NetworkResponse<WrappedListResponse<MovieResponse>, Error>.convertToResultType()
        : ResultType<List<MovieResponse>, Error> {
    return when (this) {
        is NetworkResponse.ApiError -> {
            ResultType.Error(Error(message = "Encontramos un error en la solicitud"))
        }
        is NetworkResponse.NetworkError -> {
            ResultType.Error(Error(message = "No se pudo conectar al servidor, revise su conexión a internet"))
        }
        is NetworkResponse.Success -> {
            ResultType.Success(body.results?: emptyList() )
        }
        is NetworkResponse.UnknownError -> {
            ResultType.Error(Error(message = error.message.orEmpty()))
        }
    }
}


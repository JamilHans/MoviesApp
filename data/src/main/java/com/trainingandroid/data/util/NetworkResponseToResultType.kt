package com.trainingandroid.data.util

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

fun <T> NetworkResponse<*, *>.toResultType(): ResultType<T, Error> {
    return when (this) {
        is NetworkResponse.ApiError -> ResultType.Error(
            Error(message = "Encontramos un error en la solicitud")
        )
        is NetworkResponse.NetworkError -> ResultType.Error(
            Error(message = "No se pudo conectar al servidor, revise su conexión a internet")
        )
        is NetworkResponse.Success -> ResultType.Success(this.body as T)
        is NetworkResponse.UnknownError -> ResultType.Error(
            Error(message = this.error.message.orEmpty())
        )
    }
}




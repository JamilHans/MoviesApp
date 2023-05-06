package com.trainingandroid.domain.model

sealed class Result<T>(
    open val data: T? = null,
    open val message: String? = null,
) {

    data class Success<T>(override val data: T?) : Result<T>(data)
    data class Error<T>(
        override val message: String,
        override val data: T? = null,
    ) : Result<T>(data, message)
}

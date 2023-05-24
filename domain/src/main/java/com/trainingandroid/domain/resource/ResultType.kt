package com.trainingandroid.domain.resource

sealed class ResultType<T>(
    open val data: T? = null,
    open val message: String? = null,
) {

    data class Success<T>(override val data: T?) : ResultType<T>(data)
    data class Error<T>(
        override val message: String = "",
        override val data: T? = null,
    ) : ResultType<T>(data, message)
}

package com.trainingandroid.domain.model.error

/**
 * This model is the Error that returns the services.
 */
data class Error(
    val code: Int = 0,
    val title: String = "",
    val message: String = ""
)

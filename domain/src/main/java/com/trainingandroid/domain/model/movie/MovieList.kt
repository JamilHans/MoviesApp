package com.trainingandroid.domain.model.movie

data class MovieList<T>(
    val page: Int,
    val results: List<T>? = null,
    val totalPages: Int,
)

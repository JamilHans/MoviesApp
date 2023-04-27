package com.trainingandroid.mobiedbapp.data.model.movie

data class WrappedListResponse<T>(
    val dates: Dates,
    val page: Int,
    val results: List<T>? = null,
    val total_pages: Int,
    val total_results: Int
)
package com.trainingandroid.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieListResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>? = null,
    @SerializedName("total_pages")
    val totalPages: Int,
)

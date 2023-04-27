package com.trainingandroid.mobiedbapp.presentation.detail

import com.trainingandroid.mobiedbapp.domain.model.detail.DetailMovie

data class DetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detailMovie: DetailMovie? = null
)
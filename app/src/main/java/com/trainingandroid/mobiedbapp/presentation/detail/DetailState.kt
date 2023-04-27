package com.trainingandroid.mobiedbapp.presentation.detail

import com.trainingandroid.domain.model.detail.DetailMovie

data class DetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detailMovie: DetailMovie? = null
)

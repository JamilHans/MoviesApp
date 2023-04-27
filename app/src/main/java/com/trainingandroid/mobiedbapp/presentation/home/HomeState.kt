package com.trainingandroid.mobiedbapp.presentation.home

import com.trainingandroid.mobiedbapp.domain.model.Movies

sealed class HomeState {
    class UpComingMoviesState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val upComingMovies: List<Movies>? = null
    ) : HomeState()

    class PopulateMoviesState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val populateMovies: List<Movies>? = null
    ) : HomeState()
}
package com.trainingandroid.mobiedbapp.presentation.home

import com.trainingandroid.domain.model.movie.Movies

sealed class HomeState {
    data class UpComingMoviesState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val upComingMovies: List<Movies>? = null
    ) : HomeState()

    data class PopulateMoviesState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val populateMovies: List<Movies>? = null
    ) : HomeState()
}

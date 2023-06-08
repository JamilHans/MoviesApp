package com.trainingandroid.mobiedbapp.presentation.home

import com.trainingandroid.domain.model.movie.Movies

sealed class HomeState {
    sealed class UpComingMoviesState : HomeState() {
        data class ShowLoading(val isLoading: Boolean) : UpComingMoviesState()
        data class Error(val message: String) : UpComingMoviesState()
        data class ShowUpcomingMovies(val upComingMovies: List<Movies>) : UpComingMoviesState()
    }

    sealed class PopulateMoviesState : HomeState() {
        data class ShowLoading(val isLoading: Boolean): PopulateMoviesState()
        data class Error(val message: String): PopulateMoviesState()
        data class ShowPopulateMovies(val populateMovies: List<Movies>): PopulateMoviesState()
    }
}

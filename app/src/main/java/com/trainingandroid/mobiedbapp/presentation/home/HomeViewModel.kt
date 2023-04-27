package com.trainingandroid.mobiedbapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCase
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getPopulateMoviesUseCase: GetPopulateMoviesUseCase
) : ViewModel() {

    private val _stateUpcomingMovie = MutableLiveData<HomeState.UpComingMoviesState>()
    val stateUpcomingMovie: LiveData<HomeState.UpComingMoviesState> = _stateUpcomingMovie

    private val _statePopulateMovie = MutableLiveData<HomeState.PopulateMoviesState>()
    val statePopulateMovieL: LiveData<HomeState.PopulateMoviesState> = _statePopulateMovie


    init {

        getUpcomingMovies()
        getPopulateMovies()

    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _stateUpcomingMovie.value = HomeState.UpComingMoviesState(isLoading = true)

            val response = withContext(Dispatchers.IO) {
                getUpcomingMoviesUseCase.getUpcomingMovies()
            }
            _stateUpcomingMovie.value = HomeState.UpComingMoviesState(isLoading = false)

            when (response) {
                is Result.Error -> {

                    _stateUpcomingMovie.value =
                        HomeState.UpComingMoviesState(error = response.message)
                }

                is Result.Success -> {
                    _stateUpcomingMovie.value =
                        HomeState.UpComingMoviesState(upComingMovies = response.data)
                }
            }

        }
    }

    private fun getPopulateMovies() {
        viewModelScope.launch {

            _statePopulateMovie.value = HomeState.PopulateMoviesState(isLoading = true)
            val response = withContext(Dispatchers.IO) {
                getPopulateMoviesUseCase.getPopulateMovies()
            }
            _statePopulateMovie.value = HomeState.PopulateMoviesState(isLoading = false)

            when (response) {
                is Result.Error -> {
                    _statePopulateMovie.value =
                        HomeState.PopulateMoviesState(error = response.message)
                }

                is Result.Success -> {
                    _statePopulateMovie.value =
                        HomeState.PopulateMoviesState(populateMovies = response.data)
                }
            }

        }
    }


}
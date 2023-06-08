package com.trainingandroid.mobiedbapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCase
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCase
import com.trainingandroid.mobiedbapp.presentation.util.LiveEvent
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

    private val _stateUpcomingMovie = LiveEvent<HomeState.UpComingMoviesState>()
    val stateUpcomingMovie: LiveData<HomeState.UpComingMoviesState> = _stateUpcomingMovie

    private val _statePopulateMovie = LiveEvent<HomeState.PopulateMoviesState>()
    val statePopulateMovieL: LiveData<HomeState.PopulateMoviesState> = _statePopulateMovie

    var pageNowUpcomingMovies = 1
    var pageTotalUpcomingMovies = 0

    var pageNowPopulateMovies = 1
    var pageTotalPopulateMovies = 0

    init {
        getUpcomingMovies(pageNowUpcomingMovies)
        getPopulateMovies(pageNowPopulateMovies)
    }

    fun getUpcomingMovies(page: Int) {
        viewModelScope.launch {
            _stateUpcomingMovie.value = HomeState.UpComingMoviesState.ShowLoading(isLoading = true)

            val response = withContext(Dispatchers.IO) {
                getUpcomingMoviesUseCase(page)
            }
            _stateUpcomingMovie.value = HomeState.UpComingMoviesState.ShowLoading(isLoading = false)

            when (response) {
                is ResultType.Error -> {
                    _stateUpcomingMovie.value =
                        HomeState.UpComingMoviesState.Error(message = response.value.message)
                }

                is ResultType.Success -> {
                    _stateUpcomingMovie.value =
                        HomeState.UpComingMoviesState.ShowUpcomingMovies(
                            upComingMovies = response.value.results ?: emptyList()
                        )
                    pageTotalUpcomingMovies = response.value.totalPages
                }
            }

        }
    }

    fun getPopulateMovies(page: Int) {
        viewModelScope.launch {

            _statePopulateMovie.value = HomeState.PopulateMoviesState.ShowLoading(isLoading = true)
            val response = withContext(Dispatchers.IO) {
                getPopulateMoviesUseCase(page)
            }
            _statePopulateMovie.value = HomeState.PopulateMoviesState.ShowLoading(isLoading = false)

            when (response) {
                is ResultType.Error -> {
                    _statePopulateMovie.value =
                        HomeState.PopulateMoviesState.Error(message = response.value.message)
                }

                is ResultType.Success -> {
                    _statePopulateMovie.value =
                        HomeState.PopulateMoviesState.ShowPopulateMovies(
                            populateMovies = response.value.results ?: emptyList()
                        )
                    pageTotalPopulateMovies = response.value.totalPages
                }
            }

        }
    }

}

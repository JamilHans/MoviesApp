package com.trainingandroid.mobiedbapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.usecase.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> = _state

    fun getDetailMovie(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = DetailState(isLoading = true)
            val response = withContext(Dispatchers.IO) {
                getDetailMovieUseCase(id)
            }
            _state.value = DetailState(isLoading = false)
            when (response) {
                is ResultType.Error -> {
                    _state.value = DetailState(error = response.value.message)
                }

                is ResultType.Success -> {
                    _state.value = DetailState(detailMovie = response.value)
                }
            }
        }
    }
}

package com.trainingandroid.mobiedbapp.domain.usecase

import com.trainingandroid.mobiedbapp.data.repositories.MoviesRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun getDetailMovie(id: Int) = moviesRepository.getDetailMovie(id)
}
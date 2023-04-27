package com.trainingandroid.mobiedbapp.domain.usecase

import com.trainingandroid.mobiedbapp.data.repositories.MoviesRepository
import javax.inject.Inject

class GetPopulateMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun getPopulateMovies() = moviesRepository.getPopulateMovies()
}
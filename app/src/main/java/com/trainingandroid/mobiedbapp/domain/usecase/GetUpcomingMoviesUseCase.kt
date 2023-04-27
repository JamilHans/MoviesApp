package com.trainingandroid.mobiedbapp.domain.usecase

import com.trainingandroid.mobiedbapp.data.repositories.MoviesRepository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository){

    suspend fun getUpcomingMovies() = moviesRepository.getUpcomingMovies()

}
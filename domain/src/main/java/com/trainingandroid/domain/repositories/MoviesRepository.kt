package com.trainingandroid.domain.repositories

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.movie.Movies

interface MoviesRepository {
    suspend fun getUpcomingMovies(): Result<List<Movies>>
    suspend fun getPopulateMovies(): Result<List<Movies>>
    suspend fun getDetailMovie(id: Int): Result<DetailMovie>
}
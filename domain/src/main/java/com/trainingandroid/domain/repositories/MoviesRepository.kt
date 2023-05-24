package com.trainingandroid.domain.repositories

import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.Movies

interface MoviesRepository {
    suspend fun getUpcomingMovies(): ResultType<List<Movies>, Error>
    suspend fun getPopulateMovies(): ResultType<List<Movies>, Error>
    suspend fun getDetailMovie(id: Int): ResultType<DetailMovie, Error>
}

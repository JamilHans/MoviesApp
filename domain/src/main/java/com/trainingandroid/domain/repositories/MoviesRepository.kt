package com.trainingandroid.domain.repositories

import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.MovieList
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType

interface MoviesRepository {
    suspend fun getUpcomingMovies(page: Int): ResultType<MovieList<Movies>, Error>
    suspend fun getPopulateMovies(page: Int): ResultType<MovieList<Movies>, Error>
}


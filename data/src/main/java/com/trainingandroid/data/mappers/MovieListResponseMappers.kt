package com.trainingandroid.data.mappers

import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.domain.model.movie.MovieList

fun MovieListResponse<MovieResponse>.toDomainModel() = MovieList(
    page = page,
    results = results?.map { it.toDomainModel() },
    totalPages = totalPages,
)

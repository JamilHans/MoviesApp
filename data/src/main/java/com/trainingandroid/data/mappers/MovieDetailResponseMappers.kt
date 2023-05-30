package com.trainingandroid.data.mappers

import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.detail.DetailMovie

fun MovieDetailResponse.toDomainModel(): DetailMovie = DetailMovie(
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
)

package com.trainingandroid.data.mappers

import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.domain.model.movie.Movies

fun MovieResponse.toDomainModel():
        Movies = Movies(
    adult,
    backdropPath,
    genreIds,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount,
)

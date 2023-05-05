package com.trainingandroid.data.mappers

import com.trainingandroid.data.model.moviedetail.GenreResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.data.model.moviedetail.ProductionCompanyResponse
import com.trainingandroid.data.model.moviedetail.ProductionCountryResponse
import com.trainingandroid.data.model.moviedetail.SpokenLanguageResponse
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.detail.Genre
import com.trainingandroid.domain.model.detail.ProductionCompany
import com.trainingandroid.domain.model.detail.ProductionCountry
import com.trainingandroid.domain.model.detail.SpokenLanguage

fun MovieDetailResponse.toDomainModel(): DetailMovie = DetailMovie(
    adult,
    backdropPath,
    budget,
    genreResponses.map { it.toDomainModel() },
    homepage,
    id,
    imdbId,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    productionCompanies.map { it.toDomainModel() },
    productionCountries.map { it.toDomainModel() },
    releaseDate,
    revenue,
    runtime,
    spokenLanguageResponses.map { it.toDomainModel() },
    status,
    tagline,
    title,
    video,
    voteAverage,
    voteCount,
)

private fun GenreResponse.toDomainModel(): Genre =
    Genre(id, name)


private fun ProductionCompanyResponse.toDomainModel(): ProductionCompany =
    ProductionCompany(
        id, logoPath, name, originCountry
    )


private fun ProductionCountryResponse.toDomainModel(): ProductionCountry =
    ProductionCountry(
        iso, name
    )


private fun SpokenLanguageResponse.toDomainModel(): SpokenLanguage =
    SpokenLanguage(
        englishName, iso, name
    )


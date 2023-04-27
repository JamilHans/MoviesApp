package com.trainingandroid.mobiedbapp.framework

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.detail.Genre
import com.trainingandroid.domain.model.detail.ProductionCompany
import com.trainingandroid.domain.model.detail.ProductionCountry
import com.trainingandroid.domain.model.detail.SpokenLanguage
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.mobiedbapp.data.api.RemoteService
import com.trainingandroid.mobiedbapp.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.mobiedbapp.data.model.movie.MovieResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.GenreResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.ProductionCompanyResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.ProductionCountryResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.SpokenLanguageResponse
import retrofit2.HttpException
import java.io.IOException

class MoviesRemoteDataSourceImp(private val remoteService: RemoteService) :
    MoviesRemoteDataSource {


    override suspend fun getUpcomingMovies(): Result<List<Movies>> {
        return try {
            val response = remoteService.getUpcoming()
            Result.Success(data = response.body()?.results?.map { it.toDomainModel() })
        } catch (ex: HttpException) {
            Result.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            Result.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }

    override suspend fun getPopulateMovies(): Result<List<Movies>> {
        return try {
            val response = remoteService.getPopular()
            Result.Success(data = response.body()?.results?.map { it.toDomainModel() })
        } catch (ex: HttpException) {
            Result.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            Result.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }

    override suspend fun getDetailMovie(id: Int): Result<DetailMovie> {
        return try {
            val response = remoteService.getMovieDetail(id)
            Result.Success(data = response.body()?.toDomainModel())
        } catch (ex: HttpException) {
            Result.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            Result.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }
}


private fun MovieResponse.toDomainModel():
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

private fun MovieDetailResponse.toDomainModel(): DetailMovie = DetailMovie(
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
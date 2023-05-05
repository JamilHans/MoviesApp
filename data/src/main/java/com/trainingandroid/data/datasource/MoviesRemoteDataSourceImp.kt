package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.movie.Movies
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







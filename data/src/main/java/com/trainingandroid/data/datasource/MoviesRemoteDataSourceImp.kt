package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.movie.Movies
import retrofit2.HttpException
import java.io.IOException

class MoviesRemoteDataSourceImp(private val remoteService: RemoteService) :
    MoviesRemoteDataSource {

    override suspend fun getUpcomingMovies(): ResultType<List<Movies>> {
        return try {
            val response = remoteService.getUpcoming()
            ResultType.Success(data = response.body()?.results?.map { it.toDomainModel() })
        } catch (ex: HttpException) {
            ResultType.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            ResultType.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }

    override suspend fun getPopulateMovies(): ResultType<List<Movies>> {
        return try {
            val response = remoteService.getPopular()
            ResultType.Success(data = response.body()?.results?.map { it.toDomainModel() })
        } catch (ex: HttpException) {
            ResultType.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            ResultType.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }

    override suspend fun getDetailMovie(id: Int): ResultType<DetailMovie> {
        return try {
            val response = remoteService.getMovieDetail(id)
            ResultType.Success(data = response.body()?.toDomainModel())
        } catch (ex: HttpException) {
            ResultType.Error(message = "Encontramos un error en la solicitud")
        } catch (ex: IOException) {
            ResultType.Error(message = "No se puedo conectar al servidor, revise su conexion a internet")
        }
    }
}







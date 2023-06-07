package com.trainingandroid.data.api

import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {
    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = "a270c034df12879853e01114c35c4292",
        @Query("page") page: Int
    ): NetworkResponse<MovieListResponse<MovieResponse>, Error>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String = "a270c034df12879853e01114c35c4292",
        @Query("page") page: Int
    ): NetworkResponse<MovieListResponse<MovieResponse>, Error>

    @GET("{keyword_id}?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getMovieDetail(@Path("keyword_id") id: Int): NetworkResponse<MovieDetailResponse, Error>
}

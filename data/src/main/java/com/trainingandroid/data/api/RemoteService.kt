package com.trainingandroid.data.api

import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.movie.WrappedListResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("upcoming?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getUpcoming(): NetworkResponse<WrappedListResponse<MovieResponse>, Error>

    @GET("popular?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getPopular(): NetworkResponse<WrappedListResponse<MovieResponse>, Error>

    @GET("{keyword_id}?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getMovieDetail(@Path("keyword_id") id: Int): NetworkResponse<MovieDetailResponse, Error>
}

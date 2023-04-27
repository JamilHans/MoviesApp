package com.trainingandroid.mobiedbapp.data.api

import com.trainingandroid.mobiedbapp.data.model.movie.MovieResponse
import com.trainingandroid.mobiedbapp.data.model.movie.WrappedListResponse
import com.trainingandroid.mobiedbapp.data.model.moviedetail.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("upcoming?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getUpcoming(): Response<WrappedListResponse<MovieResponse>>

    @GET("popular?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getPopular(): Response<WrappedListResponse<MovieResponse>>

    @GET("{keyword_id}?api_key=a270c034df12879853e01114c35c4292")
    suspend fun getMovieDetail(@Path("keyword_id") id: Int): Response<MovieDetailResponse>
}
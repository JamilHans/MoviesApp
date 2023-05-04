package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.Result
import com.trainingandroid.domain.model.detail.DetailMovie

interface GetDetailMovieUseCase {
    suspend fun getDetailMovie(id: Int): Result<DetailMovie>
}
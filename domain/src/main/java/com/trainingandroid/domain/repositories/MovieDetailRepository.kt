package com.trainingandroid.domain.repositories

import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

interface MovieDetailRepository {
    suspend fun getDetailMovie(id: Int): ResultType<DetailMovie, Error>

}

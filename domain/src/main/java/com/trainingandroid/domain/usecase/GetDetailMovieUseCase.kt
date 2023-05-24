package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType

interface GetDetailMovieUseCase {
    suspend operator fun invoke(id: Int): ResultType<DetailMovie, Error>

}

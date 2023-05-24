package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie

interface GetDetailMovieUseCase {
    suspend operator fun invoke(id: Int): ResultType<DetailMovie>

}

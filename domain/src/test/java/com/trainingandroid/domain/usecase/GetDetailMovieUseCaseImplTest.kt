package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.repositories.MoviesRepository
import com.trainingandroid.domain.resource.ResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetDetailMovieUseCaseImplTest {

    @Mock
    lateinit var recipeRepository: MoviesRepository
    private val detailMovie by lazy {
        DetailMovie(
            originalLanguage = "a",
            originalTitle = "a",
            overview = "s",
            popularity = 1.3,
            posterPath = "as",
            releaseDate = "d",
        )
    }

    private lateinit var sut: GetDetailMovieUseCase

    @Before
    fun setUp() {
        sut = GetDetailMovieUseCaseImpl(recipeRepository)
    }

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<DetailMovie, Error>(Error())
            val id = 1
            whenever(
                recipeRepository.getDetailMovie(id)
            ).thenReturn(
                errorResultType
            )
            val result = sut(id)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success<DetailMovie, Error>(detailMovie)
            val id = 1
            whenever(
                recipeRepository.getDetailMovie(id)
            ).thenReturn(
                successResultType
            )
            val result = sut(id)
            assertEquals(result, successResultType)
        }
    }

}

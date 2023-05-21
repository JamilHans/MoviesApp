package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.repositories.MoviesRepository
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
            adult = true,
            backdropPath = "sa",
            budget = 1,
            genres = emptyList(),
            homepage = "s",
            id = 1,
            imdbId = "s",
            originalLanguage = "a",
            originalTitle = "a",
            overview = "s",
            popularity = 1.3,
            posterPath = "as",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "d",
            revenue = 4,
            runtime = 23,
            spokenLanguages = emptyList(),
            status = "a",
            tagline = "s",
            title = "a",
            video = true,
            voteAverage = 1.2,
            voteCount = 1,
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
            val errorResultType = ResultType.Error<DetailMovie>(message = "", null)
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
            val successResultType = ResultType.Success(data = detailMovie)
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

package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.movie.Movies
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
class GetUpcomingMoviesUseCaseImplTest {

    @Mock
    lateinit var recipeRepository: MoviesRepository
    private val listUpcomingMovies: List<Movies> by lazy {
        emptyList()
    }

    private lateinit var sut: GetUpcomingMoviesUseCase

    @Before
    fun setUp() {
        sut = GetUpcomingMoviesUseCaseImpl(recipeRepository)
    }

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<List<Movies>>(message = "", null)
            whenever(
                recipeRepository.getUpcomingMovies()
            ).thenReturn(
                errorResultType
            )
            val result = sut()
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success(data = listUpcomingMovies)
            whenever(
                recipeRepository.getUpcomingMovies()
            ).thenReturn(
                successResultType
            )
            val result = sut()
            assertEquals(result, successResultType)
        }
    }

}

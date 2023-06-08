package com.trainingandroid.domain.usecase

import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.MovieList
import com.trainingandroid.domain.model.movie.Movies
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
class GetPopulateMoviesUseCaseImplTest {
    @Mock
    lateinit var recipeRepository: MoviesRepository
    private val page by lazy { 1 }
    private val listPopulateMovies: MovieList<Movies> = MovieList(
        page = page,
        results = emptyList(),
        totalPages = 2,
    )

    private lateinit var sut: GetPopulateMoviesUseCase

    @Before
    fun setUp() {
        sut = GetPopulateMoviesUseCaseImpl(recipeRepository)
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<MovieList<Movies>, Error>(Error())
            whenever(
                recipeRepository.getPopulateMovies(page = page)
            ).thenReturn(
                errorResultType
            )
            val result = sut(page = page)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success<MovieList<Movies>, Error>(listPopulateMovies)
            whenever(
                recipeRepository.getPopulateMovies(page = page)
            ).thenReturn(
                successResultType
            )
            val result = sut(page = page)
            assertEquals(result, successResultType)
        }
    }
}

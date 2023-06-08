package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
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
class MoviesRepositoryImplTest {
    @Mock
    lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    private val listMovies: MovieList<Movies> = MovieList(
        page = 1,
        results = emptyList(),
        totalPages = 2,
    )
    private var moviesListResponse: MovieListResponse<MovieResponse> = MovieListResponse(
        page = 1,
        results = emptyList(),
        totalPages = 2,
    )
    private val page by lazy { 1 }
    private lateinit var sut: MoviesRepository

    @Before
    fun setUp() {
        sut = MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Error<MovieListResponse<MovieResponse>, Error>(Error())
            val errorResultType = ResultType.Error<MovieList<Movies>, Error>(Error())
            whenever(
                moviesRemoteDataSource.getUpcomingMovies(page = page)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getUpcomingMovies(page)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Success<MovieListResponse<MovieResponse>, Error>(value = moviesListResponse)
            val successResultType = ResultType.Success<MovieList<Movies>, Error>(value = listMovies)
            whenever(
                moviesRemoteDataSource.getUpcomingMovies(page = page)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getUpcomingMovies(page = page)
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Error<MovieListResponse<MovieResponse>, Error>(Error())
            val errorResultType = ResultType.Error<MovieList<Movies>, Error>(Error())
            whenever(
                moviesRemoteDataSource.getPopulateMovies(page = page)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getPopulateMovies(page = page)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Success<MovieListResponse<MovieResponse>, Error>(value = moviesListResponse)
            val successResultType = ResultType.Success<MovieList<Movies>, Error>(value = listMovies)
            whenever(
                moviesRemoteDataSource.getPopulateMovies(page = page)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getPopulateMovies(page = page)
            assertEquals(result, successResultType)
        }
    }

}


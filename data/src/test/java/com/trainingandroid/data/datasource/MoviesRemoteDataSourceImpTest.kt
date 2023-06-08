package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.movie.MovieListResponse
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.domain.model.error.Error
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
class MoviesRemoteDataSourceImpTest {
    @Mock
    lateinit var remoteService: RemoteService
    private val page by lazy { 1 }
    private val totalPages by lazy { 2 }
    private var listMoviesResponse: MovieListResponse<MovieResponse> = MovieListResponse(
        page = page,
        results = emptyList(),
        totalPages = totalPages,
    )
    private lateinit var sut: MoviesRemoteDataSource

    @Before
    fun setUp() {
        sut = MoviesRemoteDataSourceImp(remoteService)
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val apiResult = NetworkResponse.Success(listMoviesResponse)
            val successResultType =
                ResultType.Success<MovieListResponse<MovieResponse>, Error>(listMoviesResponse)
            whenever(
                remoteService.getUpcoming(page = page)
            ).thenReturn(
                apiResult
            )
            val result = sut.getUpcomingMovies(page = page)
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val apiResult = NetworkResponse.Success(listMoviesResponse)
            val successResultType =
                ResultType.Success<MovieListResponse<MovieResponse>, Error>(listMoviesResponse)
            whenever(
                remoteService.getPopular(page = page)
            ).thenReturn(
                apiResult
            )
            val result = sut.getPopulateMovies(page = page)
            assertEquals(result, successResultType)
        }
    }

}

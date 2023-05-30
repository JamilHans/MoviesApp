package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.movie.Dates
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.movie.WrappedListResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
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
    private var listMoviesResponse: WrappedListResponse<MovieResponse> = WrappedListResponse(
        Dates("1", "1"), 1, emptyList(), 1, 2
    )

    private var detailMovie: MovieDetailResponse = (MovieDetailResponse(
        "a",
        "",
        "",
        1.0,
        "",
        "",
    ))

    private lateinit var sut: MoviesRemoteDataSource

    @Before
    fun setUp() {
        sut = MoviesRemoteDataSourceImp(remoteService)
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val apiResult = NetworkResponse.Success(listMoviesResponse)
            val successResultType = ResultType.Success<List<MovieResponse>, Error>(emptyList())
            whenever(
                remoteService.getUpcoming()
            ).thenReturn(
                apiResult
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val apiResult = NetworkResponse.Success(listMoviesResponse)
            val successResultType = ResultType.Success<List<MovieResponse>, Error>(emptyList())
            whenever(
                remoteService.getPopular()
            ).thenReturn(
                apiResult
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val apiResult = NetworkResponse.Success(detailMovie)
            val successResultType = ResultType.Success<MovieDetailResponse, Error>(detailMovie)
            whenever(
                remoteService.getMovieDetail(1)
            ).thenReturn(
                apiResult
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, successResultType)
        }
    }
}

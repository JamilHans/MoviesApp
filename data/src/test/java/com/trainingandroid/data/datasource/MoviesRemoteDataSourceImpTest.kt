package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.mappers.toDomainModel
import com.trainingandroid.data.model.movie.Dates
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.movie.WrappedListResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.resource.ResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MoviesRemoteDataSourceImpTest {
    @Mock
    lateinit var remoteService: RemoteService
    private var listMovies: Response<WrappedListResponse<MovieResponse>> =
        Response.success(
            WrappedListResponse(
                Dates("1", "1"), 1, emptyList(), 1, 2
            )
        )

    private var detailMovie: Response<MovieDetailResponse> = Response.success(
        null
    )


    private lateinit var sut: MoviesRemoteDataSource

    @Before
    fun setUp() {
        sut = MoviesRemoteDataSourceImp(remoteService)
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val successResultType =
                ResultType.Success(data = listMovies.body()?.results?.map { it.toDomainModel() })
            whenever(
                remoteService.getUpcoming()
            ).thenReturn(
                listMovies
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val successResultType =
                ResultType.Success(data = listMovies.body()?.results?.map { it.toDomainModel() })
            whenever(
                remoteService.getPopular()
            ).thenReturn(
                listMovies
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val successResultType =
                ResultType.Success(data = detailMovie.body()?.toDomainModel())
            whenever(
                remoteService.getMovieDetail(1)
            ).thenReturn(
                detailMovie
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, successResultType)
        }
    }
}

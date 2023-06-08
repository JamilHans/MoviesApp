package com.trainingandroid.data.datasource

import com.trainingandroid.data.api.NetworkResponse
import com.trainingandroid.data.api.RemoteService
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.resource.ResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MovieDetailRemoteDataSourceImpTest {
    @Mock
    lateinit var remoteService: RemoteService
    private var detailMovie: MovieDetailResponse = (MovieDetailResponse(
        "a",
        "",
        "",
        1.0,
        "",
        "",
    ))
    private lateinit var sut: MovieDetailRemoteDataSource
    @Before
    fun setUp() {
        sut = MovieDetailRemoteDataSourceImp(remoteService)
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
            Assert.assertEquals(result, successResultType)
        }
    }

}

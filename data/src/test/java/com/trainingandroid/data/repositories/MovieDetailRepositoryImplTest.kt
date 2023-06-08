package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MovieDetailRemoteDataSource
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.repositories.MovieDetailRepository
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
class MovieDetailRepositoryImplTest {
    @Mock
    lateinit var movieDetailRemoteDataSource: MovieDetailRemoteDataSource
    private val detailMovieResponse by lazy {
        MovieDetailResponse(
            originalLanguage = "a",
            originalTitle = "a",
            overview = "s",
            popularity = 1.3,
            posterPath = "as",
            releaseDate = "d",
        )
    }
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
    private lateinit var sut: MovieDetailRepository

    @Before
    fun setUp() {
        sut = MovieDetailRepositoryImpl(movieDetailRemoteDataSource)
    }

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult = ResultType.Error<MovieDetailResponse, Error>(Error())
            val errorResultType = ResultType.Error<DetailMovie, Error>(Error())
            whenever(
                movieDetailRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getDetailMovie(1)
            Assert.assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Success<MovieDetailResponse, Error>(detailMovieResponse)
            val successResultType = ResultType.Success<DetailMovie, Error>(detailMovie)
            whenever(
                movieDetailRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getDetailMovie(1)
            Assert.assertEquals(result, successResultType)
        }
    }
}

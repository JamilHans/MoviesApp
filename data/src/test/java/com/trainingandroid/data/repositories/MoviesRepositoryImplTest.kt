package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.data.model.movie.MovieResponse
import com.trainingandroid.data.model.moviedetail.MovieDetailResponse
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.model.error.Error
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
    private val listMovies: List<MovieResponse> by lazy {
        emptyList()
    }
    private val detailMovieResponse by lazy {
        MovieDetailResponse(
            adult = true,
            backdropPath = "sa",
            budget = 1,
            genreResponses = emptyList(),
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
            spokenLanguageResponses = emptyList(),
            status = "a",
            tagline = "s",
            title = "a",
            video = true,
            voteAverage = 1.2,
            voteCount = 1,
        )
    }
    private val detailMovie by lazy {
        DetailMovie(
            adult = true,
            backdropPath = "sa",
            budget = 1,
            emptyList(),
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
            emptyList(),
            status = "a",
            tagline = "s",
            title = "a",
            video = true,
            voteAverage = 1.2,
            voteCount = 1,
        )
    }
    private lateinit var sut: MoviesRepository

    @Before
    fun setUp() {
        sut = MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult = ResultType.Error<List<MovieResponse>, Error>(Error())
            val errorResultType = ResultType.Error<List<Movies>, Error>(Error())
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val dataSourceResult = ResultType.Success<List<MovieResponse>, Error>(listMovies)
            val successResultType = ResultType.Success<List<Movies>, Error>(emptyList())
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult = ResultType.Error<List<MovieResponse>, Error>(Error())
            val errorResultType = ResultType.Error<List<Movies>, Error>(Error())
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val dataSourceResult = ResultType.Success<List<MovieResponse>, Error>(listMovies)
            val successResultType = ResultType.Success<List<Movies>, Error>(emptyList())
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runBlocking {
            val dataSourceResult = ResultType.Error<MovieDetailResponse, Error>(Error())
            val errorResultType = ResultType.Error<DetailMovie, Error>(Error())
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val dataSourceResult =
                ResultType.Success<MovieDetailResponse, Error>(detailMovieResponse)
            val successResultType = ResultType.Success<DetailMovie, Error>(detailMovie)
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                dataSourceResult
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, successResultType)
        }
    }

}

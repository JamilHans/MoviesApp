package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie
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
class MoviesRepositoryImplTest {
    @Mock
    lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    private val listMovies: List<Movies> by lazy {
        emptyList()
    }
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

    private lateinit var sut: MoviesRepository

    @Before
    fun setUp() {
        sut = MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<List<Movies>>()
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                errorResultType
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success(data = listMovies)
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                successResultType
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<List<Movies>>()
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                errorResultType
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success(data = listMovies)
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                successResultType
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, successResultType)
        }
    }

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runBlocking {
            val errorResultType = ResultType.Error<DetailMovie>()
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                errorResultType
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, errorResultType)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val successResultType = ResultType.Success(data = detailMovie)
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                successResultType
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, successResultType)
        }
    }

}

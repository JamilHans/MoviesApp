package com.trainingandroid.data.repositories

import com.trainingandroid.data.datasource.MoviesRemoteDataSource
import com.trainingandroid.domain.model.Result
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
            val errorResult = Result.Error<List<Movies>>(message = "", null)
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                errorResult
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, errorResult)
        }
    }

    @Test
    fun `Getting upcoming movie should return upcoming movie when return has success`() {
        runBlocking {
            val successResult = Result.Success(data = listMovies)
            whenever(
                moviesRemoteDataSource.getUpcomingMovies()
            ).thenReturn(
                successResult
            )
            val result = sut.getUpcomingMovies()
            assertEquals(result, successResult)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runBlocking {
            val errorResult = Result.Error<List<Movies>>(message = "", null)
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                errorResult
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, errorResult)
        }
    }

    @Test
    fun `Getting populate movie should return populate movie when return has success`() {
        runBlocking {
            val successResult = Result.Success(data = listMovies)
            whenever(
                moviesRemoteDataSource.getPopulateMovies()
            ).thenReturn(
                successResult
            )
            val result = sut.getPopulateMovies()
            assertEquals(result, successResult)
        }
    }

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runBlocking {
            val errorResult = Result.Error<DetailMovie>(message = "", null)
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                errorResult
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, errorResult)
        }
    }

    @Test
    fun `Getting detail movie should return detail movie when return has success`() {
        runBlocking {
            val successResult = Result.Success(data = detailMovie)
            whenever(
                moviesRemoteDataSource.getDetailMovie(1)
            ).thenReturn(
                successResult
            )
            val result = sut.getDetailMovie(1)
            assertEquals(result, successResult)
        }
    }

}

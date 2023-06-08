package com.trainingandroid.mobiedbapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trainingandroid.domain.model.error.Error
import com.trainingandroid.domain.model.movie.MovieList
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.usecase.GetPopulateMoviesUseCase
import com.trainingandroid.domain.usecase.GetUpcomingMoviesUseCase
import com.trainingandroid.mobiedbapp.presentation.util.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val getUpcomingMoviesUseCase = mockk<GetUpcomingMoviesUseCase>()
    private val getPopulateMoviesUseCase = mockk<GetPopulateMoviesUseCase>()
    private val sut by lazy {
        HomeViewModel(getUpcomingMoviesUseCase, getPopulateMoviesUseCase)
    }
    private val page by lazy { 1 }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runTest {
            val errorResultType = ResultType.Error<MovieList<Movies>, Error>(Error())
            coEvery {
                getUpcomingMoviesUseCase(page = page)
            } returns errorResultType

            val homeState = HomeState.UpComingMoviesState.Error(
                message = errorResultType.value.message,
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase(page = page)
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should return success when return has success`() {
        runTest {
            val successResultType =
                ResultType.Success<MovieList<Movies>, Error>(
                    MovieList(
                        page = page,
                        results = emptyList(),
                        totalPages = 2
                    )
                )
            coEvery {
                getUpcomingMoviesUseCase(page = page)
            } returns successResultType

            val homeState = HomeState.UpComingMoviesState.ShowUpcomingMovies(
                upComingMovies = successResultType.value.results ?: emptyList()
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase(page = 1)
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should show loading when initialization`() {
        runTest {
            val homeState = HomeState.UpComingMoviesState.ShowLoading(
                isLoading = true,
            )
            sut.stateUpcomingMovie
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should hide loading when finished`() {
        runTest {
            val successResultType =
                ResultType.Success<MovieList<Movies>, Error>(
                    MovieList(page = page, results = emptyList(), totalPages = 2)
                )
            coEvery {
                getUpcomingMoviesUseCase(page)
            } returns successResultType

            val homeState = HomeState.UpComingMoviesState.ShowUpcomingMovies(
                upComingMovies = emptyList(),
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase(page = page)
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runTest {
            val errorResultType = ResultType.Error<MovieList<Movies>, Error>(Error())
            coEvery {
                getPopulateMoviesUseCase(page = page)
            } returns errorResultType

            val homeState = HomeState.PopulateMoviesState.Error(
                message = errorResultType.value.message,
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase(page = page)
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should return success when return has success`() {
        runTest {
            val successResultType =
                ResultType.Success<MovieList<Movies>, Error>(
                    MovieList(page = page, results = emptyList(), totalPages = 2)
                )
            coEvery {
                getPopulateMoviesUseCase(page = page)
            } returns successResultType

            val homeState = HomeState.PopulateMoviesState.ShowPopulateMovies(
                populateMovies = successResultType.value.results ?: emptyList()
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase(page = page)
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should show loading when initialization`() {
        runTest {
            val homeState = HomeState.PopulateMoviesState.ShowLoading(
                isLoading = true
            )
            sut.statePopulateMovieL
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should hide loading when finished`() {
        runTest {
            val successResultType =
                ResultType.Success<MovieList<Movies>, Error>(
                    MovieList(page = page, results = emptyList(), totalPages = 2)
                )
            coEvery {
                getPopulateMoviesUseCase(page = page)
            } returns successResultType

            val homeState = HomeState.PopulateMoviesState.ShowPopulateMovies(
                populateMovies = emptyList(),
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase(page = page)
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }
}

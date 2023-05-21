package com.trainingandroid.mobiedbapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.movie.Movies
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
        HomeViewModel(getUpcomingMoviesUseCase,getPopulateMoviesUseCase)
    }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Getting upcoming movie should return error when return has failure`() {
        runTest {
            val errorResultType = ResultType.Error<List<Movies>>(message = "", null)
            coEvery {
                getUpcomingMoviesUseCase()
            } returns errorResultType

            val homeState = HomeState.UpComingMoviesState(
                error = errorResultType.message,
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase()
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should return success when return has success`() {
        runTest {
            val successResultType = ResultType.Success<List<Movies>>(data = null)
            coEvery {
                getUpcomingMoviesUseCase()
            } returns successResultType

            val homeState = HomeState.UpComingMoviesState(
                upComingMovies = successResultType.data
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase()
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should show loading when initialization`() {
        runTest {
            val homeState = HomeState.UpComingMoviesState(
                isLoading = true,
            )
            sut.stateUpcomingMovie
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting upcoming movie should hide loading when finished`() {
        runTest {
            val successResultType = ResultType.Success<List<Movies>>(data = null)
            coEvery {
                getUpcomingMoviesUseCase()
            } returns successResultType

            val homeState = HomeState.UpComingMoviesState(
                isLoading = false
            )

            sut.stateUpcomingMovie
            coVerify {
                getUpcomingMoviesUseCase()
            }
            assertEquals(homeState, sut.stateUpcomingMovie.value)
        }
    }

    @Test
    fun `Getting populate movie should return error when return has failure`() {
        runTest {
            val errorResultType = ResultType.Error<List<Movies>>(message = "", null)
            coEvery {
                getPopulateMoviesUseCase()
            } returns errorResultType

            val homeState = HomeState.PopulateMoviesState(
                error = errorResultType.message,
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase()
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should return success when return has success`() {
        runTest {
            val successResultType = ResultType.Success<List<Movies>>(data = null)
            coEvery {
                getPopulateMoviesUseCase()
            } returns successResultType

            val homeState = HomeState.PopulateMoviesState(
                populateMovies = successResultType.data
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase()
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should show loading when initialization`() {
        runTest {
            val homeState = HomeState.PopulateMoviesState(
                isLoading = true,
            )
            sut.statePopulateMovieL
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }

    @Test
    fun `Getting populate movie should hide loading when finished`() {
        runTest {
            val successResultType = ResultType.Success<List<Movies>>(data = null)
            coEvery {
                getPopulateMoviesUseCase()
            } returns successResultType

            val homeState = HomeState.PopulateMoviesState(
                isLoading = false
            )

            sut.statePopulateMovieL
            coVerify {
                getPopulateMoviesUseCase()
            }
            assertEquals(homeState, sut.statePopulateMovieL.value)
        }
    }
}

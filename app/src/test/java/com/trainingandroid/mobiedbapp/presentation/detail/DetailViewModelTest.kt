package com.trainingandroid.mobiedbapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trainingandroid.domain.resource.ResultType
import com.trainingandroid.domain.model.detail.DetailMovie
import com.trainingandroid.domain.usecase.GetDetailMovieUseCase
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
class DetailViewModelTest {


    private val getDetailMovieUseCase = mockk<GetDetailMovieUseCase>()
    private val sut by lazy {
        DetailViewModel(getDetailMovieUseCase)
    }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Getting detail movie should return error when return has failure`() {
        runTest {
            val errorResultType = ResultType.Error<DetailMovie>()
            val id = 1
            coEvery {
                getDetailMovieUseCase(id)
            } returns errorResultType

            val detailState = DetailState(
                error = errorResultType.message,
            )

            sut.getDetailMovie(id)
            coVerify {
                getDetailMovieUseCase(id)
            }
            assertEquals(detailState, sut.state.value)
        }
    }

    @Test
    fun `Getting detail movie should return success when return has success`() {
        runTest {
            val successResultType = ResultType.Success<DetailMovie>(data = null)
            val id = 1
            coEvery {
                getDetailMovieUseCase(id)
            } returns successResultType

            val detailState = DetailState(
                detailMovie = successResultType.data,
            )

            sut.getDetailMovie(id)
            coVerify {
                getDetailMovieUseCase(id)
            }
            assertEquals(detailState, sut.state.value)
        }
    }
    @Test
    fun `Getting detail movie should show loading when initialization`() {
        runTest {
            val id = 1
            val detailState = DetailState(
                isLoading = true,
            )

            sut.getDetailMovie(id)
            assertEquals(detailState, sut.state.value)
        }
    }

    @Test
    fun `Getting detail movie should hide loading when finished`() {
        runTest {
            val successResultType = ResultType.Success<DetailMovie>(data = null)
            val id = 1
            coEvery {
                getDetailMovieUseCase(id)
            } returns successResultType

            val detailState = DetailState(
                isLoading = false,
            )

            sut.getDetailMovie(id)
            coVerify {
                getDetailMovieUseCase(id)
            }
            assertEquals(detailState, sut.state.value)
        }
    }
}

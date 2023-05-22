package com.marllons.gitusers.presenter.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marllons.gitusers.domain.usecase.GetListUsersUseCase
import com.marllons.gitusers.domain.usecase.GetUserUseCase
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.domain.model.MSHttpError
import com.marllons.mshttp.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {
    private val getListUsersUseCase = mockk<GetListUsersUseCase>()
    private val getUserUseCase = mockk<GetUserUseCase>()
    private lateinit var viewModel: MainViewModel
    private val usersExpected = listOf(UiUser(name = "User name"))
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(getListUsersUseCase, getUserUseCase)
    }

    @Test
    fun `WHEN call a function getListUiUsers THEN the data SHOULD validate with the correct movie`() =
        runTest {
            coEvery { getListUsersUseCase.invoke() } returns  Result.success(usersExpected)
            viewModel.getListUiUsers()
            assertEquals(usersExpected, viewModel.listUiUsers.value?.data)
        }


    @Test
    fun `WHEN call a function getListUiUsers THEN the data SHOULD validate with the failure`() =
        runTest {
            val error = MSHttpError()
            coEvery { getListUsersUseCase.invoke() } returns  Result.failure(error)
            viewModel.getListUiUsers()
            assertEquals(error, viewModel.listUiUsers.value?.failure)
        }

    @Test
    fun `WHEN call a function getUser THEN the data SHOULD validate with the correct movie`() =
        runTest {
            coEvery { getUserUseCase.invoke("test") } returns  Result.success(usersExpected)
            viewModel.getUser("test")
            assertEquals(usersExpected, viewModel.uiUser.value?.data)
        }


    @Test
    fun `WHEN call a function getUser THEN the data SHOULD validate with the failure`() =
        runTest {
            val error = MSHttpError()
            coEvery { getUserUseCase.invoke("test") } returns Result.failure(error)
            viewModel.getUser("test")
            assertEquals(error, viewModel.uiUser.value?.failure)
        }

}
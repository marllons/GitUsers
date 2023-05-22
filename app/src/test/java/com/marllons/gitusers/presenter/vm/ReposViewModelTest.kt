package com.marllons.gitusers.presenter.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marllons.gitusers.domain.usecase.GetListReposUseCase
import com.marllons.gitusers.presenter.entity.UiRepo
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
class ReposViewModelTest {
    private val getListReposUseCase = mockk<GetListReposUseCase>()
    private lateinit var viewModel: ReposViewModel
    private val repoExpected = listOf(UiRepo(name = "Name Repo"))
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ReposViewModel(getListReposUseCase)
    }

    @Test
    fun `WHEN call a function getListRepos THEN the data SHOULD validate with the correct movie`() =
        runTest {
            coEvery { getListReposUseCase.invoke("test") } returns  Result.success(repoExpected)
            viewModel.getListRepos("test")
            assertEquals(repoExpected, viewModel.uiListRepos.value?.data)
        }


    @Test
    fun `WHEN call a function getUser THEN the data SHOULD validate with the failure`() =
        runTest {
            val error = MSHttpError()
            coEvery { getListReposUseCase.invoke("test") } returns Result.failure(error)
            viewModel.getListRepos("test")
            assertEquals(error, viewModel.uiListRepos.value?.failure)
        }

    @Test
    fun `WHEN call a function filterList with correct filter THEN the data SHOULD be filtered correctly`() {
        val result = viewModel.filterList(repoExpected, "Name Repo")
        assertEquals(repoExpected, result)
    }

    @Test
    fun `WHEN call a function filterList with incorrect filter THEN the data SHOULD be return an empty list`() {
        val result = viewModel.filterList(repoExpected, "test")
        assertEquals(listOf<UiRepo>(), result)
        assertTrue(result.isEmpty())
    }


}
package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.domain.entity.Repo
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.mshttp.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class GetListReposUseCaseImplTest {
    private val repository = mockk<GitUsersRepository>()
    private lateinit var useCase: GetListReposUseCaseImpl
    private val repos = listOf(Repo(name = "Repo name"), Repo(name = "Repo name2"))
    private val reposExpected = listOf(UiRepo(name = "Repo name"), UiRepo(name = "Repo name2"))

    @Before
    fun setUp() {
        useCase = GetListReposUseCaseImpl(repository)
    }

    @Test
    fun `WHEN call invoke function THEN the result SHOULD be correct`() = runTest {
        coEvery { repository.getReposList("999") } returns Result.success(repos)
        val result = useCase.invoke("999")
        assertEquals(Result.success(reposExpected), result)
    }
}
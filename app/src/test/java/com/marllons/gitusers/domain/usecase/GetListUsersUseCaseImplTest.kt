package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.domain.entity.Repo
import com.marllons.gitusers.domain.entity.User
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.entity.UiUser
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
class GetListUsersUseCaseImplTest {
    private val repository = mockk<GitUsersRepository>()
    private lateinit var useCase: GetListUsersUseCaseImpl
    private val users = listOf(User(name = "User name"), User(name = "User name2"))
    private val usersExpected = listOf(UiUser(name = "User name"), UiUser(name = "User name2"))

    @Before
    fun setUp() {
        useCase = GetListUsersUseCaseImpl(repository)
    }

    @Test
    fun `WHEN call invoke function THEN the result SHOULD be correct`() = runTest {
        coEvery { repository.getUsersList() } returns Result.success(users)
        val result = useCase.invoke()
        assertEquals(Result.success(usersExpected), result)
    }
}
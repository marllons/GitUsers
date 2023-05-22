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
class GetUserUseCaseImplTest{
    private val repository = mockk<GitUsersRepository>()
    private lateinit var useCase: GetUserUseCaseImpl
    private val user = User(name = "User name")
    private val usersExpected = listOf(UiUser(name = "User name"))

    @Before
    fun setUp() {
        useCase = GetUserUseCaseImpl(repository)
    }

    @Test
    fun `WHEN call invoke function THEN the result SHOULD be correct`() = runTest {
        coEvery { repository.getUser("999") } returns Result.success(user)
        val result = useCase.invoke("999")
        assertEquals(Result.success(usersExpected), result)
    }
}
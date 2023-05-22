package com.marllons.gitusers.data.repository

import com.marllons.gitusers.data.mapper.toEntityDomain
import com.marllons.gitusers.data.model.response.ReposResponse
import com.marllons.gitusers.data.model.response.UserResponse
import com.marllons.gitusers.data.service.GitUsersService
import com.marllons.mshttp.result.GENERIC_ERROR
import com.marllons.mshttp.result.RetrofitResultImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GitUsersRepositoryImplTest {
    private val retrofitResult = RetrofitResultImpl(mockk(relaxed = true))
    private val service: GitUsersService = mockk()
    private val subject = GitUsersRepositoryImpl(retrofitResult, service)

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getUsersList THEN call a service`() = runTest {
        coEvery { service.getListUsers() } returns Response.error(400, mockk(relaxed = true))
        subject.getUsersList()
        coVerify(exactly = 1) {
            service.getListUsers()
        }
    }

    @Test
    fun `GIVEN a call on getUsersList WHEN has an error on service THEN return error`() = runTest {
        coEvery { service.getListUsers() } returns Response.error(400, mockk(relaxed = true))
        val result = subject.getUsersList()
        assertEquals(GENERIC_ERROR, result.errorOrNull())
    }


    @Test
    fun `GIVEN a call on getUsersList WHEN has a success on service THEN return success mapped`() = runTest {
        val responseMock = mockk<List<UserResponse>>(relaxed = true) {
            every { this@mockk.first().followers } returns Random.nextInt()
        }
        val expected = responseMock.map {
            it.toEntityDomain()
        }
        coEvery { service.getListUsers() } returns Response.success(responseMock)
        val result = subject.getUsersList()
        assertEquals(expected, result.getOrNull())
        coVerify(exactly = 1) {
            service.getListUsers()
        }
    }

    @Test
    fun `GIVEN a call on getUser THEN call a service`() = runTest {
        coEvery { service.getUser("test") } returns Response.error(400, mockk(relaxed = true))
        subject.getUser("test")
        coVerify(exactly = 1) {
            service.getUser("test")
        }
    }

    @Test
    fun `GIVEN a call on getUser WHEN has an error on service THEN return error`() = runTest {
        coEvery { service.getUser(any()) } returns Response.error(400, mockk(relaxed = true))
        val result = subject.getUser("test")
        assertEquals(GENERIC_ERROR, result.errorOrNull())
    }


    @Test
    fun `GIVEN a call on getUser WHEN has a success on service THEN return success mapped`() = runTest {
        val responseMock = mockk<UserResponse>(relaxed = true) {
            every { this@mockk.followers } returns Random.nextInt()
        }
        val expected = responseMock.toEntityDomain()
        coEvery { service.getUser("test") } returns Response.success(responseMock)
        val result = subject.getUser("test")
        assertEquals(expected, result.getOrNull())
        coVerify(exactly = 1) {
            service.getUser("test")
        }
    }

    @Test
    fun `GIVEN a call on getUserRepos THEN call a service`() = runTest {
        coEvery { service.getReposList("test") } returns Response.error(400, mockk(relaxed = true))
        subject.getReposList("test")
        coVerify(exactly = 1) {
            service.getReposList("test")
        }
    }

    @Test
    fun `GIVEN a call on getUserRepos WHEN has an error on service THEN return error`() = runTest {
        coEvery { service.getReposList(any()) } returns Response.error(400, mockk(relaxed = true))
        val result = subject.getReposList("test")
        assertEquals(GENERIC_ERROR, result.errorOrNull())
    }


    @Test
    fun `GIVEN a call on getUserRepos WHEN has a success on service THEN return success mapped`() = runTest {
        val responseMock = mockk<List<ReposResponse>>(relaxed = true) {
            every { this@mockk.first().id } returns Random.nextInt()
        }
        val expected = responseMock.map {
            it.toEntityDomain()
        }
        coEvery { service.getReposList("test") } returns Response.success(responseMock)
        val result = subject.getReposList("test")
        assertEquals(expected, result.getOrNull())
        coVerify(exactly = 1) {
            service.getReposList("test")
        }
    }
}
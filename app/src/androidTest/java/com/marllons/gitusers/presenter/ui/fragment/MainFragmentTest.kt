package com.marllons.gitusers.presenter.ui.fragment

import android.content.Context
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marllons.gitusers.di.gitUsersModules
import com.marllons.mshttp.MsHttpModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import com.marllons.gitusers.R
import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.domain.entity.User
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class MainFragmentTest : AutoCloseKoinTest() {
    private val testContext: Context by lazy { ApplicationProvider.getApplicationContext() }
    private val repository = mockk<GitUsersRepository>(relaxed = true)
    private val mockUserList = listOf(User(name = "user 1"), User(name = "user 2"))

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidLogger(Level.ERROR)
        androidContext(testContext)
        modules(listOf(MsHttpModules.all, gitUsersModules))
    }

    @Before
    fun setup() {
        launchFragmentInContainer<MainFragment>(themeResId = R.style.Theme_GitUsers)
        coEvery { repository.getUsersList() } returns Result.success(mockUserList)
    }

    @Test
    fun should_show_users_when_view_is_created() {

        onView(
            withId(R.id.recyclerView)
        ).check(matches(isDisplayed()))
    }
}
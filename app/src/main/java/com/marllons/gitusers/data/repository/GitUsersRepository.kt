package com.marllons.gitusers.data.repository

import com.marllons.gitusers.domain.entity.Repo
import com.marllons.gitusers.domain.entity.User
import com.marllons.mshttp.result.Result

interface GitUsersRepository {
    suspend fun getUsersList(): Result<List<User>>
    suspend fun getUser(username: String): Result<User>
    suspend fun getReposList(username: String): Result<List<Repo>>
}
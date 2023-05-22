package com.marllons.gitusers.data.repository

import com.marllons.gitusers.data.mapper.toEntityDomain
import com.marllons.gitusers.data.service.GitUsersService
import com.marllons.gitusers.domain.entity.Repo
import com.marllons.gitusers.domain.entity.User
import com.marllons.mshttp.result.Result
import com.marllons.mshttp.result.RetrofitResult

class GitUsersRepositoryImpl(
    private val result: RetrofitResult,
    private val service: GitUsersService
) : GitUsersRepository {

    override suspend fun getUsersList(): Result<List<User>> {
        return result.getResult {
            service.getListUsers()
        }.map { list ->
            list.map {
                it.toEntityDomain()
            }
        }
    }

    override suspend fun getUser(username: String): Result<User> {
        return result.getResult {
            service.getUser(username)
        }.map {
            it.toEntityDomain()
        }
    }

    override suspend fun getReposList(username: String): Result<List<Repo>> {
        return result.getResult {
            service.getReposList(username)
        }.map { list ->
            list.map {
                it.toEntityDomain()
            }
        }
    }
}
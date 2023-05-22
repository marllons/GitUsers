package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.data.mapper.toEntityPresenter
import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

class GetListReposUseCaseImpl(
    private val repository: GitUsersRepository
): GetListReposUseCase {

    override suspend fun invoke(username: String): Result<List<UiRepo>> {
        return repository.getReposList(username).map { list ->
            list.map {
                it.toEntityPresenter()
            }
        }
    }

}
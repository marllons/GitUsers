package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.data.mapper.toEntityPresenter
import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

class GetListUsersUseCaseImpl(
    private val repository: GitUsersRepository
): GetListUsersUseCase {

    override suspend fun invoke(): Result<List<UiUser>> {
        return repository.getUsersList().map { list ->
            list.map {
                it.toEntityPresenter()
            }
        }
    }

}
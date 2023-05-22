package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.data.mapper.toEntityPresenter
import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

class GetUserUseCaseImpl(
    private val repository: GitUsersRepository
): GetUserUseCase {

    override suspend fun invoke(username: String): Result<List<UiUser>> {
        return repository.getUser(username).map {
            listOf(it.toEntityPresenter())
        }
    }

}
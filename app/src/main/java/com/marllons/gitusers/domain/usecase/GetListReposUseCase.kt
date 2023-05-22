package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

interface GetListReposUseCase {
    suspend operator fun invoke(username: String): Result<List<UiRepo>>
}
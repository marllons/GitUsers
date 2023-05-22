package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

interface GetUserUseCase {
    suspend operator fun invoke(username: String): Result<List<UiUser>>
}
package com.marllons.gitusers.domain.usecase

import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.result.Result

interface GetListUsersUseCase {
    suspend operator fun invoke(): Result<List<UiUser>>
}
package com.marllons.gitusers.data.mapper

import com.marllons.gitusers.data.model.response.ReposResponse
import com.marllons.gitusers.domain.entity.Repo
import com.marllons.gitusers.presenter.entity.UiRepo

fun ReposResponse.toEntityDomain() = Repo(
    id = this.id,
    name = this.name,
    htmlUrl = this.htmlUrl,
    description = this.description,
    stargazersCount = this.stargazersCount,
    forksCount = this.forksCount,
    watchersCount = this.watchersCount,
    visibility = this.visibility,
    defaultBranch = this.defaultBranch,
    language = this.language
)

fun Repo.toEntityPresenter() = UiRepo(
    id = this.id,
    name = this.name,
    htmlUrl = this.htmlUrl,
    description = this.description,
    stargazersCount = this.stargazersCount,
    forksCount = this.forksCount,
    watchersCount = this.watchersCount,
    visibility = this.visibility,
    defaultBranch = this.defaultBranch,
    language = this.language
)

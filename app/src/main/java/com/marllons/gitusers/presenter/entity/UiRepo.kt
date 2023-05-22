package com.marllons.gitusers.presenter.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiRepo(
    val id: Int? = null,
    val name: String? = null,
    val htmlUrl: String? = null,
    val description: String? = null,
    val stargazersCount: Int? = null,
    val forksCount: Int? = null,
    val watchersCount: Int? = null,
    val visibility: String? = null,
    val defaultBranch: String? = null,
    val language: String? = null
) : Parcelable
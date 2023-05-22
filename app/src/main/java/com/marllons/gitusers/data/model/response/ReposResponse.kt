package com.marllons.gitusers.data.model.response


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ReposResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,
    @SerializedName("forks_count")
    val forksCount: Int? = null,
    @SerializedName("watchers_count")
    val watchersCount: Int? = null,
    @SerializedName("visibility")
    val visibility: String? = null,
    @SerializedName("default_branch")
    val defaultBranch: String? = null,
    @SerializedName("language")
    val language: String? = null
) : Parcelable
package com.marllons.gitusers.data.mapper

import com.marllons.gitusers.data.model.response.UserResponse
import com.marllons.gitusers.domain.entity.User
import com.marllons.gitusers.presenter.entity.UiUser

fun UserResponse.toEntityDomain() = User(
    login = this.login,
    id = this.id,
    nodeId = this.nodeId,
    avatarUrl = this.avatarUrl,
    gravatarId = this.gravatarId,
    url = this.url,
    htmlUrl = this.htmlUrl,
    followersUrl = this.followersUrl,
    followingUrl = this.followingUrl,
    gistsUrl = this.gistsUrl,
    starredUrl = this.starredUrl,
    subscriptionsUrl = this.subscriptionsUrl,
    organizationsUrl = this.organizationsUrl,
    reposUrl = this.reposUrl,
    eventsUrl = this.eventsUrl,
    receivedEventsUrl = this.receivedEventsUrl,
    type = this.type,
    siteAdmin = this.siteAdmin,
    name = this.name,
    company = this.company,
    blog = this.blog,
    location = this.location,
    email = this.email,
    hireable = this.hireable,
    bio = this.bio,
    twitterUsername = this.twitterUsername,
    publicRepos = this.publicRepos,
    publicGists = this.publicGists,
    followers = this.followers,
    following = this.following,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun User.toEntityPresenter() = UiUser(
    login = this.login,
    id = this.id,
    nodeId = this.nodeId,
    avatarUrl = this.avatarUrl,
    gravatarId = this.gravatarId,
    url = this.url,
    htmlUrl = this.htmlUrl,
    followersUrl = this.followersUrl,
    followingUrl = this.followingUrl,
    gistsUrl = this.gistsUrl,
    starredUrl = this.starredUrl,
    subscriptionsUrl = this.subscriptionsUrl,
    organizationsUrl = this.organizationsUrl,
    reposUrl = this.reposUrl,
    eventsUrl = this.eventsUrl,
    receivedEventsUrl = this.receivedEventsUrl,
    type = this.type,
    siteAdmin = this.siteAdmin,
    name = this.name,
    company = this.company,
    blog = this.blog,
    location = this.location,
    email = this.email,
    hireable = this.hireable,
    bio = this.bio,
    twitterUsername = this.twitterUsername,
    publicRepos = this.publicRepos,
    publicGists = this.publicGists,
    followers = this.followers,
    following = this.following,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

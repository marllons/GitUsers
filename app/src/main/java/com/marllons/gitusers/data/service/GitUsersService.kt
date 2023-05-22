package com.marllons.gitusers.data.service

import com.marllons.gitusers.data.model.response.ReposResponse
import com.marllons.gitusers.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUsersService {

    @GET("/users")
    suspend fun getListUsers(): Response<List<UserResponse>>

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<UserResponse>

    @GET("/users/{username}/repos")
    suspend fun getReposList(@Path("username") username: String): Response<List<ReposResponse>>

}
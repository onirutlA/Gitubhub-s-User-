package com.onirutla.githubsuser.data.source.remote.network

import com.onirutla.githubsuser.data.source.remote.response.SearchResponse
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET(value = "search/users")
    suspend fun getUsersSearch(
        @Query(value = "q") username: String
    ): Response<SearchResponse>

    @GET(value = "users/{username}")
    suspend fun getUserDetail(
        @Path(value = "username") username: String
    ): Response<UserResponse>

    @GET(value = "users/{username}/followers")
    suspend fun getUserFollowers(
        @Path(value = "username") username: String
    ): Response<List<UserResponse>>

    @GET(value = "users/{username}/following")
    suspend fun getUserFollowing(
        @Path(value = "username") username: String
    ): Response<List<UserResponse>>

}
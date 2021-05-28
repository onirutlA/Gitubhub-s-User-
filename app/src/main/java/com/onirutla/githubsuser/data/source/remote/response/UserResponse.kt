package com.onirutla.githubsuser.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    @Json(name = "login") val username: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "followers_url") val followersUrl: String?,
    @Json(name = "following") val following: Int?,
    @Json(name = "blog") val blog: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "followers") val followers: Int?,
    @Json(name = "following_url") val followingUrl: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "public_repos") val publicRepos: Int?,
    @Json(name = "twitter_username") val twitterUsername: String?,
    @Json(name = "type") val type: String?

)
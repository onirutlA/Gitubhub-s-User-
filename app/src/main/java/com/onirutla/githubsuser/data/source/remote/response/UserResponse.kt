package com.onirutla.githubsuser.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    @Json(name = "login") val username: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "followers_url") val followersUrl: String? = null,
    @Json(name = "following") val following: Int? = null,
    @Json(name = "blog") val blog: String? = null,
    @Json(name = "company") val company: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "followers") val followers: Int? = null,
    @Json(name = "following_url") val followingUrl: String? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "public_repos") val publicRepos: Int? = null,
    @Json(name = "twitter_username") val twitterUsername: String? = null,
    @Json(name = "type") val type: String? = null

)
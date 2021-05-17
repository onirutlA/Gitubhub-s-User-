package com.onirutla.githubsuser.data.source.remote.response

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "id") val id: String,
    @Json(name = "username") var username: String,
    @Json(name = "avatar_url") var avatarUrl: String,
    @Json(name = "follower_url") var followerUrl: String,
    @Json(name = "following_url") var followingUrl: String,
    @Json(name = "followers") var followers: Int,
    @Json(name = "following") var following: Int,
    @Json(name = "login") var name: String,
    @Json(name = "company") var company: String,
    @Json(name = "blog") var blog: String,
    @Json(name = "location") var location: String,
    @Json(name = "twitter_username") var twitter: String
)
package com.onirutla.githubsuser.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo val id: String,
    @ColumnInfo var isFavorite: Boolean,
    @ColumnInfo var username: String,
    @ColumnInfo(name = "avatar_url") var avatarUrl: String,
    @ColumnInfo(name = "follower_url") var followerUrl: String,
    @ColumnInfo(name = "following_url") var followingUrl: String,
    @ColumnInfo var name: String,
    @ColumnInfo var company: String,
    @ColumnInfo var blog: String,
    @ColumnInfo var location: String,
    @ColumnInfo var twitter: String
)
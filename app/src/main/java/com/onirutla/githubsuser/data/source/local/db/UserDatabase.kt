package com.onirutla.githubsuser.data.source.local.db

import androidx.room.Database
import com.onirutla.githubsuser.data.source.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase {
    abstract fun userDao(): UserDao
}
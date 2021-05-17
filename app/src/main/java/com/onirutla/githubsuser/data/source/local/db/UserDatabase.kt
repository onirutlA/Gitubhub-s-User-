package com.onirutla.githubsuser.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onirutla.githubsuser.data.source.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}
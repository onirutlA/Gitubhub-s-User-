package com.onirutla.githubsuser.data.source.local.db

import androidx.room.*
import com.onirutla.githubsuser.data.source.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addUsers(users: List<UserEntity>)

    @Insert
    suspend fun addUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users")
    suspend fun getAllUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    suspend fun getAllUserFavorite(): Flow<List<UserEntity>>
}
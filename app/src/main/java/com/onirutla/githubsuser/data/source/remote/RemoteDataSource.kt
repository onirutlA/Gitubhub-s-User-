package com.onirutla.githubsuser.data.source.remote

import android.util.Log
import com.onirutla.githubsuser.data.source.remote.network.GithubApiService
import com.onirutla.githubsuser.data.source.remote.response.SearchResponse
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val apiService: GithubApiService
) {
    fun getUsersSearch(username: String): Flow<SearchResponse> = flow {
        val response = apiService.getUsersSearch(username)
        Log.d("user search", "${response.body()!!.items}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getUserDetail(username: String): Flow<UserResponse> = flow {
        val response = apiService.getUserDetail(username)
        Log.d("user detail", "${response.body()}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getUsersFollowing(username: String): Flow<List<UserResponse>> = flow {
        val response = apiService.getUserFollowing(username)
        Log.d("following", "${response.body()}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getUserFollowers(username: String): Flow<List<UserResponse>> = flow {
        val response = apiService.getUserFollowers(username)
        Log.d("follower", "${response.body()}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)
}
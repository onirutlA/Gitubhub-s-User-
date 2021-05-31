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
        Log.d("remote data source", "${response.body()!!.items}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getUserDetail(username: String): Flow<UserResponse> = flow {
        val response = apiService.getUserDetail(username)
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }

    fun getUsersFollowers(username: String): Flow<List<UserResponse>> = flow {
        val response = apiService.getUserFollowers(username)
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }

    fun getUserFollowers(username: String): Flow<List<UserResponse>> = flow {
        val response = apiService.getUserFollowing(username)
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }
}
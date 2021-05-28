package com.onirutla.githubsuser.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onirutla.githubsuser.data.source.remote.RemoteDataSource
import com.onirutla.githubsuser.data.source.remote.response.SearchResponse
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    fun getUserSearch(username: String): LiveData<SearchResponse> =
        remoteDataSource.getUsersSearch(username)
}
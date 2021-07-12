package com.onirutla.githubsuser.view.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.githubsuser.data.source.remote.RemoteDataSource
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ViewModel() {

    private val _userFollowers: MutableStateFlow<List<UserResponse>> = MutableStateFlow(emptyList())
    private val userFollowers: StateFlow<List<UserResponse>> = _userFollowers

    fun getUserFollowers(username: String): StateFlow<List<UserResponse>> {
        viewModelScope.launch(Dispatchers.IO) {
            remoteDataSource.getUserFollowers(username).collect {
                _userFollowers.value = it
            }
        }
        return userFollowers
    }
}
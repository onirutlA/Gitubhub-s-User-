package com.onirutla.githubsuser.view.detail.following

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
class FollowingViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
    ) : ViewModel() {

    private val _userFollowing: MutableStateFlow<List<UserResponse>> = MutableStateFlow(emptyList())
    private val userFollowing: StateFlow<List<UserResponse>> = _userFollowing

    fun getUserFollowing(username: String): StateFlow<List<UserResponse>> {
        viewModelScope.launch(Dispatchers.IO) {
            remoteDataSource.getUsersFollowing(username).collect {
                _userFollowing.value = it
            }
        }
        return userFollowing
    }

}
package com.onirutla.githubsuser.view.detail

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
class DetailViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ViewModel() {

    private val _userDetail: MutableStateFlow<UserResponse> = MutableStateFlow(UserResponse())
    private val userDetail: StateFlow<UserResponse> = _userDetail

    fun getUserDetail(username: String): StateFlow<UserResponse> {
        viewModelScope.launch(Dispatchers.IO){
            remoteDataSource.getUserDetail(username).collect {
                _userDetail.value = it
            }
        }
        return userDetail
    }
}
package com.onirutla.githubsuser.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.githubsuser.data.source.remote.RemoteDataSource
import com.onirutla.githubsuser.data.source.remote.response.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel() {

    private val _userSearch: MutableStateFlow<SearchResponse> = MutableStateFlow(SearchResponse())
    private val userSearch: StateFlow<SearchResponse> = _userSearch

    fun getUserSearch(username: String): StateFlow<SearchResponse>{
        viewModelScope.launch(Dispatchers.IO){
            remoteDataSource.getUsersSearch(username).collect {
                _userSearch.value = it
            }
        }
        return userSearch
    }
}
package com.example.mvvm_demo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_demo.data.DataRepository
import com.example.mvvm_demo.data.NetworkResult
import com.example.mvvm_demo.model.BaseResponse
import com.example.mvvm_demo.model.PhotoModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoListViewModel(private val context: Context, private val dataRepository: DataRepository) :
    ViewModel() {

    private val _photoListState: MutableStateFlow<NetworkResult<BaseResponse<List<PhotoModel>>>> =
        MutableStateFlow(NetworkResult.Loading(loading = false))
    val photoListState: StateFlow<NetworkResult<BaseResponse<List<PhotoModel>>>> = _photoListState
    private var job: Job? = null

    fun getPhotos(offset: Int) {
        job = viewModelScope.launch {
            dataRepository.getPhotos(offset, 20).collect {
                _photoListState.value = it
            }
        }
    }
}
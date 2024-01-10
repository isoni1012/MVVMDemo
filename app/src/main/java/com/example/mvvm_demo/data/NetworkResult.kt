package com.example.mvvm_demo.data

sealed class NetworkResult<T> {

    data class Success<T>(val data: T) : NetworkResult<T>()

    data class Fail<T>(val message: String) : NetworkResult<T>()

    data class Loading<T>(val loading : Boolean) : NetworkResult<T>()
    class NoInternet<T> : NetworkResult<T>()
}
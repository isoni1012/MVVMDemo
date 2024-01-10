package com.example.mvvm_demo.model

data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val total_photos: Int,
    val offset: Int,
    val limit: Int,
    val photos: T
)

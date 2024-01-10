package com.example.mvvm_demo.data.api

import com.example.mvvm_demo.model.BaseResponse
import com.example.mvvm_demo.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<BaseResponse<List<PhotoModel>>>
}
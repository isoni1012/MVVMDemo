package com.example.mvvm_demo.data

import android.content.Context
import android.util.Log
import com.example.mvvm_demo.data.api.ApiService
import com.example.mvvm_demo.data.local.DemoDao
import com.example.mvvm_demo.helper.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import java.net.SocketTimeoutException

class DataRepository(
    private val context: Context,
    private val apiService: ApiService,
    private val demoDao: DemoDao
) {

    suspend fun getPhotos(offset: Int, limit: Int) = flow {

        if (!Utility.isInternetAvailable(context)) {
            emit(NetworkResult.NoInternet())
            return@flow
        }

        emit(NetworkResult.Loading(loading = true))
        val response = apiService.getPhotos(offset,limit)

        response.body()?.let { body ->
            Log.e("photo size before storing --->", "${demoDao.getPhotos().size}")
            if (body.photos.isNotEmpty()) {
                demoDao.insertPhotos(body.photos)
            }
            Log.e("photo size after storing --->", "${demoDao.getPhotos().size}")
            emit(NetworkResult.Success(data = body))
            return@flow
        }

        response.errorBody()?.let { errorBody ->
            val jObjError = org.json.JSONObject(errorBody.string())
            emit(NetworkResult.Fail(message = jObjError.getString("message").toString()))
            return@flow
        }
    }.catch { exc ->
        if (exc is ConnectException || exc is SocketTimeoutException) {
            emit(NetworkResult.NoInternet())
        } else {
            emit(NetworkResult.Fail(exc.message ?: "Try again"))
        }
    }.flowOn(Dispatchers.IO)
}
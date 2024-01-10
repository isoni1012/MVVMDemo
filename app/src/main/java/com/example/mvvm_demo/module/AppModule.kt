package com.example.mvvm_demo.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mvvm_demo.data.DataRepository
import com.example.mvvm_demo.data.api.ApiService
import com.example.mvvm_demo.data.local.DemoDao
import com.example.mvvm_demo.data.local.DemoDatabase
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideDatabase(get()) }
    single { provideDao(get()) }
    single { provideDataRepository(get(), get(),get()) }
}

//create okHttpClient object
private fun provideOkhttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        .protocols(listOf(Protocol.HTTP_1_1))
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS).build()
}

//create retrofit object
private fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl("https://api.slingacademy.com/v1/sample-data/")
        .addConverterFactory(GsonConverterFactory.create()).client(client)
        .build()

//create api service object
private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

//create database object
private fun provideDatabase(context: Application): DemoDatabase =
    Room.databaseBuilder(context, DemoDatabase::class.java, "demo").fallbackToDestructiveMigration()
        .build()

//create dao object
private fun provideDao(database: DemoDatabase): DemoDao = database.getDao()

//create data repository object
private fun provideDataRepository(context: Context, apiService: ApiService,demoDao: DemoDao): DataRepository =
    DataRepository(context, apiService,demoDao)
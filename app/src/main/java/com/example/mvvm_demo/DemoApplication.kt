package com.example.mvvm_demo

import android.app.Application
import com.example.mvvm_demo.module.appModule
import com.example.mvvm_demo.module.photoListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DemoApplication)
            modules(appModule, photoListModule)
        }
    }
}
package com.example.mvvm_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_demo.model.PhotoModel

@Database(entities = [PhotoModel::class], version = 1)
abstract class DemoDatabase : RoomDatabase(){
    abstract fun getDao() : DemoDao
}
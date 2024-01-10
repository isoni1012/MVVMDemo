package com.example.mvvm_demo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_demo.model.PhotoModel

@Dao
interface DemoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhotos(photos: List<PhotoModel>)

    @Query("SELECT * FROM photoModel")
    fun getPhotos() : List<PhotoModel>
}
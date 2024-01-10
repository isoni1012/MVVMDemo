package com.example.mvvm_demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoModel")
data class PhotoModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val url: String,
    val title: String,
    val description: String
)

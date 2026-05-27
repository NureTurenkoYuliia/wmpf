package com.example.myfirstapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,
    val content: String,
    val createdAt: String
)
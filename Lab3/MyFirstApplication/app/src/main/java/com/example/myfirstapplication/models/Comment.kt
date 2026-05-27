package com.example.myfirstapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,
    val postId: Int,
    val content: String,
    val createdAt: String
)
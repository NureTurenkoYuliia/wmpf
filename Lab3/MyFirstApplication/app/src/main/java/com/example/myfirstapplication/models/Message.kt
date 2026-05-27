package com.example.myfirstapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val chatId: Int,
    val userId: Int,
    val text: String,
    val createdAt: String
)
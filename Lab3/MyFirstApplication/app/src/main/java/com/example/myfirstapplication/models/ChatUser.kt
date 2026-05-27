package com.example.myfirstapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_users")
data class ChatUser(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val chatId: Int,
    val userId: Int
)
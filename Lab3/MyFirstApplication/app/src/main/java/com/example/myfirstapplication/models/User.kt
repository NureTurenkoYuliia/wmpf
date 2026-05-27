package com.example.myfirstapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userName: String,
    val email: String,
    val dateOfBirth: String,
    val bio: String
)
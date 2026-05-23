package com.example.myfirstapplication.api

import com.example.myfirstapplication.models.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts/1")
    suspend fun getPost(): Post
}
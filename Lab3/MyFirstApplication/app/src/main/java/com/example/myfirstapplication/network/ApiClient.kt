package com.example.myfirstapplication.network

import okhttp3.OkHttpClient

object ApiClient {

    val client: OkHttpClient by lazy {

        OkHttpClient.Builder()

            .addInterceptor { chain ->

                val request =
                    chain.request()
                        .newBuilder()
                        .header("Accept-Encoding", "gzip")
                        .build()

                chain.proceed(request)
            }

            .build()
    }
}
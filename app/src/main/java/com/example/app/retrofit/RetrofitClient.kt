package com.example.app.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val client = OkHttpClient.Builder().build()

    val dailyMotionInstance by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.dailymotion.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        retrofit.create(Service::class.java)
    }


    val githubInstance by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        retrofit.create(Service::class.java)
    }
}
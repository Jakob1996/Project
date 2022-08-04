package com.example.app.retrofit

import com.example.app.data.FirstItem
import com.example.app.data.ListOfSecondItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("/users")
    fun getFirstItems(): Deferred<Response<FirstItem>>

    @GET("/users")
    fun getSecondItems(): Deferred<Response<ListOfSecondItem>>
}
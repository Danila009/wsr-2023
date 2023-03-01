package com.example.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/Menu")
    suspend fun getMenu(): Response<Unit?>

    @POST("/Authorization")
    suspend fun auth(
        @Body body: AuthBody
    ): Response<Unit?>
}

val api by lazy {
    Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<Api>()
}

data class AuthBody(
    val email: String,
    val password: String
)
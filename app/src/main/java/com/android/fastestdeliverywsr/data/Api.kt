package com.android.fastestdeliverywsr.data

import com.android.fastestdeliverywsr.data.model.MenItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/Menu/Items")
    suspend fun getMenu(): Response<List<MenItem>>

    @POST("/Authorization")
    suspend fun auth(@Body auth: Auth): Response<Unit?>

    @POST("/Registration")
    suspend fun reg(@Body reg: Reg): Response<Unit?>
}

val api by lazy {
    Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<Api>()
}

data class Auth(
    val email:String,
    val password:String
)

data class Reg(
    val email:String,
    val password:String,
    val fio:String,
    val phone:Int
)
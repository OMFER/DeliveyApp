package com.omfer.deliveryapp.routes

import com.omfer.deliveryapp.models.ResponseHTTP
import com.omfer.deliveryapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersRoutes {

    @POST("users/register")
    fun register(@Body user: User): Call<ResponseHTTP>

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<ResponseHTTP>

}
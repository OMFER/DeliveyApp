package com.omfer.deliveryapp.providers

import com.omfer.deliveryapp.api.ApiRoutes
import com.omfer.deliveryapp.models.ResponseHTTP
import com.omfer.deliveryapp.models.User
import com.omfer.deliveryapp.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {
    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHTTP>? {
        return usersRoutes?.register(user)
    }
    fun login(email: String, password: String): Call<ResponseHTTP>? {
        return usersRoutes?.login(email, password)
    }

}
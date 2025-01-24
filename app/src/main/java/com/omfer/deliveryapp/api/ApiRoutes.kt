package com.omfer.deliveryapp.api

import com.omfer.deliveryapp.routes.UsersRoutes

class ApiRoutes {

    val retrofit = RetrofilClient()
    val apiURL = "http://192.168.100.46:3000/api/" //Sujeto a cambios
    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(apiURL).create(UsersRoutes::class.java)
    }
}
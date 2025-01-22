package com.omfer.deliveryapp.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseHTTP (
    @SerializedName("message") val message: String,
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("id") val id: JsonObject,
    @SerializedName("error") val error: String,
){
    override fun toString(): String {
        return "ResponseHTTP(message='$message', isSuccess=$isSuccess, id=$id, error='$error')"
    }
}
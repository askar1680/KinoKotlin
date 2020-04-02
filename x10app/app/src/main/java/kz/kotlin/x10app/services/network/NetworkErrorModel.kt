package kz.kotlin.x10app.services.network

import com.google.gson.annotations.SerializedName

data class NetworkErrorModel(
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: String
)
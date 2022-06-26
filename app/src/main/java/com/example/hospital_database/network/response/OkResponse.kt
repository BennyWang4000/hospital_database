package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

data class OkResponse(
    @SerializedName("code")
    val code: Int
)
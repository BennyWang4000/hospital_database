package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class DepartmentResponse : ArrayList<DepartmentResponse.DepartmentResponseItem>(){
    data class DepartmentResponseItem(
        @SerializedName("dep_id")
        val depId: Int,
        @SerializedName("dep_name")
        val depName: String
    )
}
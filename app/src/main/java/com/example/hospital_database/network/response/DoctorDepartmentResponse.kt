package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class DoctorDepartmentResponse : ArrayList<DoctorDepartmentResponse.DoctorDepartmentResponseItem>(){
    data class DoctorDepartmentResponseItem(
        @SerializedName("doc_id")
        val docId: Int,
        @SerializedName("doc_name")
        val docName: String,
        @SerializedName("dep_id")
        val depId: Int,
        @SerializedName("dep_name")
        val depName: String
    )
}
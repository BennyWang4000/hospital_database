package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class DoctorResponse : ArrayList<DoctorResponse.DoctorResponseItem>(){
    data class DoctorResponseItem(
        @SerializedName("doc_id")
        val docId: Int,
        @SerializedName("doc_name")
        val docName: String,
        @SerializedName("doc_desc")
        val docDesc: Any,
        @SerializedName("dep_id")
        val depId: Int
    )
}
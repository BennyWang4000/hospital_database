package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class PatientResponse : ArrayList<PatientResponse.PatientResponseItem>(){
    data class PatientResponseItem(
        @SerializedName("p_id")
        val pId: String,
        @SerializedName("med_id")
        val medId: Int,
        @SerializedName("pat_name")
        val patName: String,
        @SerializedName("pat_date")
        val patDate: Long
    )
}
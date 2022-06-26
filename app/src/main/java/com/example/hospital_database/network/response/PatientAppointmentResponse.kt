package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class PatientAppointmentResponse : ArrayList<PatientAppointmentResponse.PatientAppointmentResponseItem>(){
    data class PatientAppointmentResponseItem(
        @SerializedName("cli_id")
        val cliId: Int,
        @SerializedName("cli_date")
        val cliDate: Long,
        @SerializedName("cli_day")
        val cliDay: Int,
        @SerializedName("cli_time")
        val cliTime: Int,
        @SerializedName("doc_name")
        val docName: String,
        @SerializedName("dep_name")
        val depName: String
    )
}
package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class ClinicAppointmentPatientResponse : ArrayList<ClinicAppointmentPatientResponse.ClinicAppointmentPatientResponseItem>(){
    data class ClinicAppointmentPatientResponseItem(
        @SerializedName("pat_name")
        val patName: String
    )
}
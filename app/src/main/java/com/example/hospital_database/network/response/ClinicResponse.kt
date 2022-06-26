package com.example.hospital_database.network.response


import com.google.gson.annotations.SerializedName

class ClinicResponse : ArrayList<ClinicResponse.ClinicResponseItem>(){
    data class ClinicResponseItem(
        @SerializedName("cli_id")
        val cliId: Int,
        @SerializedName("cli_date")
        val cliDate: Long,
        @SerializedName("cli_day")
        val cliDay: Int,
        @SerializedName("cli_time")
        val cliTime: Int

    )
}
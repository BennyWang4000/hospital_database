package com.example.hospital_database.network

import com.example.hospital_database.network.response.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectPatient(@Body requestBody: RequestBody): Response<PatientResponse>

    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectDoctorDepartment(@Body requestBody: RequestBody): Response<DoctorDepartmentResponse>

    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectDepartment(@Body requestBody: RequestBody): Response<DepartmentResponse>

    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectClinic(@Body requestBody: RequestBody): Response<ClinicResponse>

    @Headers("Content-Type: application/json")
    @POST("insert")
    suspend fun insertAppointment(@Body requestBody: RequestBody): Response<OkResponse>

    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectPatientAppointment(@Body requestBody: RequestBody): Response<PatientAppointmentResponse>

    @Headers("Content-Type: application/json")
    @POST("select")
    suspend fun selectClinicAppointmentPatient(@Body requestBody: RequestBody): Response<ClinicAppointmentPatientResponse>


}
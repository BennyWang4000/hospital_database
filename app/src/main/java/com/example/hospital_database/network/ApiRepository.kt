package com.example.hospital_database.network

import com.example.hospital_database.network.response.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import kotlin.collections.*

class ApiRepository(
    private val apiService: ApiService
) {
    private fun <T>createJson(vararg params: Pair<String, T>) =
        JSONObject(mapOf(*params))
            .toString()
            .toRequestBody("application/json; charset=utf-8"
                .toMediaTypeOrNull())

    suspend fun selectPatient(query: String): Response<PatientResponse> {
        return apiService.selectPatient(createJson(
            "query" to query))
    }

    suspend fun selectDoctorDepartment(): Response<DoctorDepartmentResponse>{
        return apiService.selectDoctorDepartment(createJson(
            "query" to "select doc_id, doc_name, doctor.dep_id, dep_name from doctor inner join department on doctor.dep_id=department.dep_id"
        ))
    }

    suspend fun selectDepartment(): Response<DepartmentResponse>{
        return apiService.selectDepartment(createJson(
            "query" to "select * from department"
        ))
    }

    suspend fun selectClinicWhere(docId: Int, cliDay: Int, cliTime: Int): Response<ClinicResponse>{
        return apiService.selectClinic(createJson(
            "query" to "select cli_id, cli_date, cli_day, cli_time from clinic where doc_id=$docId and cli_day=$cliDay and cli_time=$cliTime"
        ))
    }

    suspend fun insertAppointment(medId: Int, cliId: Int): Response<OkResponse>{
        return apiService.insertAppointment(createJson(
            "query" to "insert into appointments (med_id, cli_id) values ($medId, $cliId)"
        ))
    }

    suspend fun selectPatientAppointment(medId: Int): Response<PatientAppointmentResponse>{
        return apiService.selectPatientAppointment(createJson(
            "query" to "select cli_id, cli_date, cli_day, cli_time, doc_name, dep_name from (select cli_id, cli_date, cli_day, cli_time, d.doc_name, d.dep_id from (select * from clinic where cli_id in (select cli_id from appointments where med_id=$medId)) as c inner join doctor as d on d.doc_id=c.doc_id) as cd inner join department on department.dep_id=cd.dep_id"
        ))
    }

    suspend fun selectClinicAppointmentPatient(cliId: Int): Response<ClinicAppointmentPatientResponse>{
        return apiService.selectClinicAppointmentPatient(createJson(
                "query" to "select pat_name from (select med_id, cli_id from (select med_id, clinic.cli_id from clinic inner join appointments on clinic.cli_id=appointments.cli_id) as ca where cli_id=$cliId) as cam inner join patient on cam.med_id=patient.med_id"
        ))
    }


}
package com.example.hospital_database.ui.patient_appointment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.network.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.HttpException
import timber.log.Timber

class PatientAppointmentActivity : AppCompatActivity(), AppointmentRecyclerAdapter.OnItemClickListener {
    private val apiRepository: ApiRepository by inject()
    private var medId= 0
    private lateinit var recyclerAppointmentOthers: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_appointment)
        medId= (intent.extras?.get("med_id") as Int?)!!

        val recyclerAppointment: RecyclerView= findViewById(R.id.patient_recycler_appointment)
        recyclerAppointmentOthers= findViewById(R.id.patient_recycler_appointment_others)

        GlobalScope.launch(Dispatchers.Main) {

            val result = apiRepository.selectPatientAppointment(medId)
            if (!result.isSuccessful) throw HttpException(result)
            Timber.d(result.body().toString())

            val recyclerAdapter =
                result.body()?.let {
                    AppointmentRecyclerAdapter(
                        this@PatientAppointmentActivity,
                        it,
                        this@PatientAppointmentActivity
                    )
                }

            runOnUiThread {
                recyclerAppointment.apply {
                    adapter = recyclerAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    override fun onItemClick(cliId: Int) {
        GlobalScope.launch(Dispatchers.Main) {

            val result = apiRepository.selectClinicAppointmentPatient(cliId)
            if (!result.isSuccessful) throw HttpException(result)
            Timber.d(result.body().toString())

            val recyclerAdapter =
                result.body()?.let {
                    AppointmentOthersRecyclerAdapter(this@PatientAppointmentActivity, it)
                }

            runOnUiThread {
                recyclerAppointmentOthers.apply {
                    adapter = recyclerAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
}
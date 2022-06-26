package com.example.hospital_database.ui.main.main_pager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.network.ApiRepository
import com.example.hospital_database.ui.patient_appointment.PatientAppointmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.HttpException
import timber.log.Timber

class MainPagerFragment(): Fragment() {

    private var position: Int= 0
    private val apiRepository: ApiRepository by inject()

    constructor(position: Int) : this() {
        this.position = position
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var root: View
        when (this.position){
            0 -> {
                root = inflater.inflate(R.layout.fragment_main_registration, container, false)

                val btnDoc: Button = root.findViewById(R.id.main_registration_btn_doctor)
//                val btnDep: Button = root.findViewById(R.id.main_registration_btn_department)
                val recyclerView: RecyclerView = root.findViewById(R.id.main_registration_recyclerview)

                btnDoc.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Main) {
                        val result = apiRepository.selectDoctorDepartment()
                        if (!result.isSuccessful) throw HttpException(result)

                        Timber.d(result.body().toString())

                        val recyclerAdapter=
                            context?.let { it1 -> result.body()?.let { it2 ->
                                MainRecyclerAdapter(it1,it2)
                            } }

                        activity?.runOnUiThread {
                            recyclerView.apply{
                                adapter=recyclerAdapter
                                layoutManager= LinearLayoutManager(context)
                            }
                        }
                    }
                }


            }
            1 -> {
                root = inflater.inflate(R.layout.fragment_main_appointment, container, false)

                val editId: EditText = root.findViewById(R.id.main_appointment_edittext_id)
//                val editYear: EditText = root.findViewById(R.id.main_appointment_edittext_year)
//                val editMonth: EditText = root.findViewById(R.id.main_appointment_edittext_month)
//                val editDay: EditText = root.findViewById(R.id.main_appointment_edittext_day)

                val btnSearch: Button = root.findViewById(R.id.main_appointment_btn_search)

                btnSearch.setOnClickListener {

                    val intent= Intent(this.activity, PatientAppointmentActivity::class.java)
                    intent.putExtra("med_id", editId.text.toString().toInt())
                    startActivity(intent)
//                    GlobalScope.launch(Dispatchers.Main) {
//                        val result = apiRepository.selectPatient("select * from patient")
//                        if (!result.isSuccessful) throw HttpException(result)
//
//                        activity?.runOnUiThread {
//                            btnSearch.text = "Done!"
//                        }
//
//                        Timber.d(result.body().toString())
//                    }
                }


            }
        }
        return root
    }
}
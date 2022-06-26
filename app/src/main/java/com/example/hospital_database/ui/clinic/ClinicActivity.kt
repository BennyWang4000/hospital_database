package com.example.hospital_database.ui.clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

class ClinicActivity : AppCompatActivity(), ClinicRecyclerAdapter.OnItemClickListener {
    private val apiRepository: ApiRepository by inject()
    var cliId= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic)

        val docId: Int?= intent.extras?.get("doc_id") as Int?

        val editId: EditText= findViewById(R.id.clinic_edittext_id)
//        val editYear: EditText= findViewById(R.id.clinic_edittext_year)
//        val editMonth: EditText= findViewById(R.id.clinic_edittext_month)
//        val editDay: EditText= findViewById(R.id.clinic_edittext_day)
        val btnRegister: Button = findViewById(R.id.clinic_btn_register)

        btnRegister.setOnClickListener{
            GlobalScope.launch(Dispatchers.Main) {
                val result = apiRepository.insertAppointment(medId = editId.text.toString().toInt(), cliId)
                if (!result.isSuccessful) throw HttpException(result)
                Timber.d(result.body().toString())
            }
        }

        val recyclerMondayMorning: RecyclerView = findViewById(R.id.clinic_recycler_monday_morning)
        val recyclerMondayAfternoon: RecyclerView = findViewById(R.id.clinic_recycler_monday_afternoon)
        val recyclerTuesdayMorning: RecyclerView = findViewById(R.id.clinic_recycler_tuesday_morning)
        val recyclerTuesdayAfternoon: RecyclerView = findViewById(R.id.clinic_recycler_tuesday_afternoon)
        val recyclerWednesdayMorning: RecyclerView = findViewById(R.id.clinic_recycler_wednesday_morning)
        val recyclerWednesdayAfternoon: RecyclerView = findViewById(R.id.clinic_recycler_wednesday_afternoon)
        val recyclerThursdayMorning: RecyclerView = findViewById(R.id.clinic_recycler_thursday_morning)
        val recyclerThursdayAfternoon: RecyclerView = findViewById(R.id.clinic_recycler_thursday_afternoon)
        val recyclerFridayMorning: RecyclerView = findViewById(R.id.clinic_recycler_friday_morning)
        val recyclerFridayAfternoon: RecyclerView = findViewById(R.id.clinic_recycler_friday_afternoon)


        val recyclerList= arrayListOf<ArrayList<RecyclerView>>(
            arrayListOf(recyclerMondayMorning,
                recyclerTuesdayMorning,
                recyclerWednesdayMorning,
                recyclerThursdayMorning,
                recyclerFridayMorning),
            arrayListOf(recyclerMondayAfternoon,
                recyclerTuesdayAfternoon,
                recyclerWednesdayAfternoon,
                recyclerThursdayAfternoon,
                recyclerFridayAfternoon)
        )

        GlobalScope.launch(Dispatchers.Main) {

            for(time in 0..1){
                for(day in 1..5){
                    val result = apiRepository.selectClinicWhere(docId!!, cliDay = day, cliTime = time)
                    if (!result.isSuccessful) throw HttpException(result)
                    Timber.d(result.body().toString())

                    val recyclerAdapter=
                        result.body()?.let { ClinicRecyclerAdapter(this@ClinicActivity, it, this@ClinicActivity) }

                    runOnUiThread {
                        recyclerList[time][day- 1].apply{
                            adapter=recyclerAdapter
                            layoutManager= LinearLayoutManager(context)
                        }
                    }
                }
            }




        }
    }

    override fun onItemClick(cliId: Int) {
        this.cliId= cliId

        Toast.makeText(this, this.cliId.toString(), Toast.LENGTH_SHORT).show()
    }
}
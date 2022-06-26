package com.example.hospital_database.ui.patient_appointment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.network.response.ClinicAppointmentPatientResponse

class AppointmentOthersRecyclerAdapter(
        private val context: Context,
        private val data: ClinicAppointmentPatientResponse
): RecyclerView.Adapter<AppointmentOthersRecyclerAdapter.AppointmentOthersViewHolder>() {
    inner class AppointmentOthersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName: TextView= itemView.findViewById(R.id.item_patient_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentOthersViewHolder {
        return AppointmentOthersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_patient_appointment_others, parent, false))
    }

    override fun onBindViewHolder(holder: AppointmentOthersViewHolder, position: Int) {
        holder.apply {
            textName.text= data[position].patName
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
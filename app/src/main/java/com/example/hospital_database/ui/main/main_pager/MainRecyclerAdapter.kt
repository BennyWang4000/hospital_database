package com.example.hospital_database.ui.main.main_pager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.ui.clinic.ClinicActivity
import com.example.hospital_database.network.response.DoctorDepartmentResponse
import timber.log.Timber

class MainRecyclerAdapter(
    private val context: Context,
    private val data: DoctorDepartmentResponse,
): RecyclerView.Adapter<MainRecyclerAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textDoc: TextView = itemView.findViewById(R.id.main_registration_item_textview_doctor)
        val textDep: TextView = itemView.findViewById(R.id.main_registration_item_textview_department)
        val btnEnter: Button = itemView.findViewById(R.id.main_registration_item_btn)
    }
//    private class DepartmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val textId: TextView = itemView.findViewById(R.id.textId)
//        val btnClick: Button = itemView.findViewById(R.id.btnClick)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_registration_doctor, parent, false))
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.apply {
            textDoc.text= data[position].docName
            Timber.d(data[position].docName)
            textDep.text= data[position].depName
            btnEnter.setOnClickListener {
                val intent= Intent(context, ClinicActivity::class.java)
                intent.putExtra("doc_id", data[position].docId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
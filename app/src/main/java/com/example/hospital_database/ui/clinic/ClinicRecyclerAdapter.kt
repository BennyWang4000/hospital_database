package com.example.hospital_database.ui.clinic

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.network.response.ClinicResponse
import java.text.SimpleDateFormat
import java.util.*

class ClinicRecyclerAdapter(
    private val context: Context,
    private val data: ClinicResponse,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<ClinicRecyclerAdapter.ClinicViewHolder>() {
    inner class ClinicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val textFirst: TextView = itemView.findViewById(R.id.clinic_item_textview_first)
        val textSecond: TextView = itemView.findViewById(R.id.clinic_item_textview_second)
//        val btnEnter: Button = itemView.findViewById(R.id.clinic_item_btn)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(data[position].cliId)
            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(cliId: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        return ClinicViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_clinic, parent, false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        val formatter= SimpleDateFormat("MMM/dd")
        holder.apply {
            textFirst.text= formatter.format(Date(data[position].cliDate)).toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
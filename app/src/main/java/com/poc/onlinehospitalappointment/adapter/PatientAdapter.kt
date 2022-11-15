package com.poc.onlinehospitalappointment.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Patient
import kotlinx.android.synthetic.main.appointmentd_recycler_view.view.*

class PatientAdapter(private val PatientList: ArrayList<Patient>) :
    RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PImg: ImageView = itemView.findViewById(R.id.dc_10)
        val DName: TextView = itemView.findViewById(R.id.dc_john)
        val PDate: TextView = itemView.findViewById(R.id.dc_d)
        val PDes: TextView = itemView.findViewById(R.id.dc_des)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.treatment_card_view2, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val Patient = PatientList[position]
        holder.DName.text = Patient.DName
        holder.PDate.text = Patient.PDate
        holder.PDes.text = Patient.PDes
        holder.PImg.setImageResource(Patient.PImg)

    }


    override fun getItemCount(): Int {
        return PatientList.size


    }
}



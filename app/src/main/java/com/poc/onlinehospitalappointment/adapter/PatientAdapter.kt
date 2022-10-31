package com.poc.onlinehospitalappointment.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Patient

class PatientAdapter(private val PatientList: ArrayList<Patient>) :
    RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
//        holder.DBtn.setOnClickListener {
//            val intent = Intent(it.context,Booking::class.java)
//            intent.putExtra("position",holder.adapterPosition)
//            it.context.startActivity(intent)
//        }

    }


    override fun getItemCount(): Int {
        return PatientList.size


    }
}



package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Approvedad

class ApprovedadAdapter (private val  ApprovedPatientList: ArrayList<Approvedad>) : RecyclerView.Adapter<ApprovedadAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val APName: TextView = itemView.findViewById(R.id.ap_john)
        val APDate: TextView = itemView.findViewById(R.id.ap_d)
        val APDes: TextView = itemView.findViewById(R.id.ap_des)
        val APBtn: Button =itemView.findViewById(R.id.vt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context).inflate(R.layout.approvedad_card_view5, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Patient = ApprovedPatientList[position]
        holder.APName.text = Patient.APName
        holder.APDate.text = Patient.APDate
        holder.APDes.text = Patient.APDes
        holder.APBtn.text=Patient.APBtn
//        holder.DBtn.setOnClickListener {
//            val intent = Intent(it.context,Booking::class.java)
//            intent.putExtra("position",holder.adapterPosition)
//            it.context.startActivity(intent)
//        }

    }


    override fun getItemCount(): Int {
        return ApprovedPatientList.size


    }
}
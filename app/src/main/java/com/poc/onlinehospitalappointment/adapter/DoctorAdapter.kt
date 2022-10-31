package com.poc.onlinehospitalappointment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.ui.activity.Booking

class DoctorAdapter(private val DoctorList: ArrayList<Doctor>) : RecyclerView.Adapter<DoctorAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var DImage: ImageView = itemView.findViewById(R.id.dc_1)
        val DName: TextView = itemView.findViewById(R.id.dc_name)
        val DSpec:TextView = itemView.findViewById(R.id.dc_spec)
        val DDes: TextView = itemView.findViewById(R.id.dc_spe)
        val DDeg: TextView = itemView.findViewById(R.id.dc_deg)
        val DBtn: Button = itemView.findViewById(R.id.dc_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context).inflate(R.layout.appointmentd_card_view, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Doctor = DoctorList[position]
        holder.DImage.setImageResource(Doctor.DImage)
        holder.DName.text = Doctor.DName
        holder.DSpec.text = Doctor.DSpec
        holder.DDes.text = Doctor.DDes
        holder.DDeg.text = Doctor.DDeg
        holder.DBtn.text = Doctor.DBtn
        holder.DBtn.setOnClickListener {
            val intent = Intent(it.context, Booking::class.java)
            intent.putExtra("position",holder.adapterPosition)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return DoctorList.size
    }


}

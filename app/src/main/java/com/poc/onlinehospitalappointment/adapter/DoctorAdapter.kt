package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.listeners.AppointmentCallback

class DoctorAdapter(
    private val DoctorList: ArrayList<Doctor>,
    private val appointmentCallback: AppointmentCallback
) : RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var DImage: ImageView = itemView.findViewById(R.id.dc_1)
        val DName: TextView = itemView.findViewById(R.id.dc_name)
        val DSpec: TextView = itemView.findViewById(R.id.dc_spec)
        val DDes: TextView = itemView.findViewById(R.id.dc_spe)
        val DDeg: TextView = itemView.findViewById(R.id.dc_deg)
        val DBtn: AppCompatTextView = itemView.findViewById(R.id.dc_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.appointmentd_card_view, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val doctor = DoctorList[position]
        holder.DImage.setImageResource(doctor.DImage)
        holder.DName.text = doctor.DName
        holder.DSpec.text = doctor.DSpec
        holder.DDes.text = doctor.DDes
        holder.DDeg.text = doctor.DDeg
//        holder.DBtn.text = doctor.DBtn

        holder.DBtn.setOnClickListener {
            appointmentCallback.onBookClick(DoctorList!![position])
            //fun onBookClick(view: View) {

//            val intent = Intent(it.context, BookingFragment::class.java)
//            intent.putExtra("position",holder.adapterPosition)
//            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return DoctorList.size
    }


}

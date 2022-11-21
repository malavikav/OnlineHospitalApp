package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.data.DoctorList
import com.poc.onlinehospitalappointment.listeners.AppointmentCallback

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    private val userList = ArrayList<DoctorList>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.appointmentd_card_view,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.fname.text = currentitem.fname
//        holder.DImage
//        holder.DSpec.text = currentitem.DSpec
//        holder.DDes.text = currentitem.DDes
//        holder.DDeg.text = currentitem.DDeg


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<DoctorList>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val fname : TextView = itemView.findViewById(R.id.dc_name)
//        val DSpec : TextView = itemView.findViewById(R.id.dc_spec)
//        val DDes : TextView = itemView.findViewById(R.id.dc_spe)
//        val DDeg : TextView = itemView.findViewById(R.id.dc_deg)
    }

}

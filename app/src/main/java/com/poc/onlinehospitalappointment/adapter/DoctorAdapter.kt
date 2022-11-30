package com.poc.onlinehospitalappointment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.data.DoctorList
import com.poc.onlinehospitalappointment.listeners.AppointmentCallback
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.appointmentd_recycler_view.view.*

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    private val userList = ArrayList<DoctorList>()
    private var sharedPreferance:SharedPreferenceClass
    init {
        sharedPreferance = SharedPreferenceClass
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.appointmentd_card_view,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]
//        holder.roundCardView. =currentitem.dImage


      //  Log.e("USER_IMAGE","USER_IMAGE" +url)
      //  Glide.with(this).load(url).into(this)

        holder.fname.text = currentitem.fname
        holder.DSpec.text = "Specialization:-"
        holder.DDes.text = "Description:-"
        holder.DDeg.text = "Degree:-"
      //  holder.userImage.setImageBitmap(url) = currentitem.userImage
       // Picasso.get().load(url).into(holder.userImage)
       // Log.e("url","url"+url)
        //holder.userImage.setImageURI(url) = currentitem.userImage
        Glide.with(holder.userImage.getContext())
            .load(currentitem.userImage.toString())
            .into(holder.userImage)

        Log.e("current item",""+currentitem)
       // holder.userImage.text = currentitem.userImage.toString()
       // holder.userImage


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
        val DSpec : TextView = itemView.findViewById(R.id.dc_spec)
        val DDes : TextView = itemView.findViewById(R.id.dc_spe)
        val DDeg : TextView = itemView.findViewById(R.id.dc_deg)
       val userImage : ImageView = itemView.findViewById(R.id.dc_1)
    }

}

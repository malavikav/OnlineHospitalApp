package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Request

class RequestAdapter: RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {

    private val userList = ArrayList<Request>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.request_card_view3,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]
        currentitem.pImage?.let { holder.pImage.setImageResource(it) }
        holder.patientName.text = currentitem.patientName
        holder.patientId.text = currentitem.patientId
        holder.doctorName.text = currentitem.doctorName
        holder.doctorId.text = currentitem.doctorId
        holder.date.text = currentitem.date
        holder.time.text = currentitem.time
        holder.status.text = currentitem.status
        holder.description.text = currentitem.description


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<Request>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var pImage: ImageView = itemView.findViewById(R.id.dc_11)
        val patientName : TextView = itemView.findViewById(R.id.p_rosh)
        val patientId : TextView = itemView.findViewById(R.id.p_id)
        val doctorName : TextView = itemView.findViewById(R.id.p_dr)
        val doctorId : TextView = itemView.findViewById(R.id.p_did)
        val date : TextView = itemView.findViewById(R.id.p_dt)
        val time : TextView = itemView.findViewById(R.id.p_time)
        val status : TextView = itemView.findViewById(R.id.p_sts)
        val description : TextView = itemView.findViewById(R.id.p_des)

    }

}


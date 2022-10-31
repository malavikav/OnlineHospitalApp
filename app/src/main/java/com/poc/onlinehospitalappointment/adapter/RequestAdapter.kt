package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Request

class RequestAdapter (private val  RequestList: ArrayList<Request>) : RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val   PName:TextView=itemView.findViewById(R.id.p_rosh)
        val PDescription: TextView = itemView.findViewById(R.id.p_des)
        val PDrdetails: TextView = itemView.findViewById(R.id.p_dr)
        val PDateTime: TextView = itemView.findViewById(R.id.p_dt)
        val PStatus: TextView = itemView.findViewById(R.id.p_sts)
        val PApprove:Button=itemView.findViewById(R.id.a_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context).inflate(R.layout.request_card_view3, parent, false)
        return MyViewHolder(cardItem)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Request = RequestList[position]
        holder.PName.text = Request.PName
        holder.PDescription.text = Request.PDescription
        holder.PDrdetails.text = Request.PDrdetails
        holder.PDateTime.text = Request.PDateTime
        holder.PStatus.text = Request.PStatus
        holder.PApprove.text=Request.PApprove


//      holder.PApprove.setOnClickListener {
//          val intent = Intent(it.context,ApprovedMain::class.java)
//            intent.putExtra("position",holder.adapterPosition)
//            it.context.startActivity(intent)
//       }

    }


    override fun getItemCount(): Int {
        return RequestList.size


    }
}


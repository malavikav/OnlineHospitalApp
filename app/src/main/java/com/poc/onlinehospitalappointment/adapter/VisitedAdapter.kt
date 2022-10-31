package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Visited

class VisitedAdapter (private val  VisitedList: ArrayList<Visited>) : RecyclerView.Adapter<VisitedAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val VPName: TextView = itemView.findViewById(R.id.vp_john)
        val VPDate: TextView = itemView.findViewById(R.id.vp_d)
        val VPvis: TextView = itemView.findViewById(R.id.vp_des)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context).inflate(R.layout.visited_card_view6, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Patient = VisitedList[position]
        holder.VPName.text = Patient.VPName
        holder.VPDate.text = Patient.VPDate
        holder.VPvis.text = Patient.VPVis
//        holder.DBtn.setOnClickListener {
//            val intent = Intent(it.context,Booking::class.java)
//            intent.putExtra("position",holder.adapterPosition)
//            it.context.startActivity(intent)
//        }

    }


    override fun getItemCount(): Int {
        return VisitedList.size


    }
}

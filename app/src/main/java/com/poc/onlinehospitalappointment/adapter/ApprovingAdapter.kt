package com.poc.onlinehospitalappointment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.data.Approving

class ApprovingAdapter(private val ApprovingList: ArrayList<Approving>) :
    RecyclerView.Adapter<ApprovingAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val AName: TextView = itemView.findViewById(R.id.p_rosh1)
        val ADescription: TextView = itemView.findViewById(R.id.p_des1)
        val ADrdetails: TextView = itemView.findViewById(R.id.p_dr1)
        val ADateTime: TextView = itemView.findViewById(R.id.p_dt1)
        val AStatus: TextView = itemView.findViewById(R.id.p_sts1)
        val ACancel: Button = itemView.findViewById(R.id.c_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val cardItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.approving_card_view4, parent, false)
        return MyViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Approving = ApprovingList[position]
        holder.AName.text = Approving.AName
        holder.ADescription.text = Approving.ADescription
        holder.ADrdetails.text = Approving.ADrdetails
        holder.ADateTime.text = Approving.ADateTime
        holder.AStatus.text = Approving.AStatus
        holder.ACancel.text = Approving.ACancel
//        holder.ACancel.setOnClickListener {
//            val intent = Intent(it.context, RequestAdapter::class.java)
//            intent.putExtra("position", holder.adapterPosition)
//            it.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return ApprovingList.size
    }
}
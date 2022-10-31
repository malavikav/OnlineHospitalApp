package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.ApprovingAdapter
import com.poc.onlinehospitalappointment.data.Approving

class ApprovingFragment:Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var ApprovingList: ArrayList<Approving>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.approving_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view4)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ApprovingList = ArrayList()
        ApprovingList.add(
            Approving(
                "Roshan Reddi",
                "fever,chills",
                "Dr Johnsmith",
                "10-10-2022 11:00 am",
                "Approved", "canecl"
            )
        )

        ApprovingList.add(
            Approving(
                "Rashmika mandanna",
                "tiredness,headache",
                "Dr Rishab Shetty",
                "10-11-2022 12:00pm",
                "Approved", "cancel"
            )
        )
        ApprovingList.add(
            Approving(
                "Sudeep reddi",
                "headache",
                "Dr Prashanti",
                "10-11-2022 10:00am",
                "Approved", "cancel"
            )
        )
        ApprovingList.add(
            Approving(
                "Ruchitha shetty",
                "vomitting,diarrhea",
                "Dr Rakshith Shetty",
                "12-11-2022 4:00pm",
                "Approved", "cancel"
            )
        )
        ApprovingList.add(
            Approving(
                "Prashanth PM",
                "watery red eyes",
                "Dr Veeresh", "13-11-2022 1:00pm",
                "Approved", "cancel"
            )
        )
        ApprovingList.add(
            Approving(
                "Prakruthi Reddi",
                "dry caugh",
                "Dr Mahesh patil",
                "13-11-2022 10:00am",
                "Approved", "cancel"
            )
        )
        ApprovingList.add(
            Approving(
                "Abhinav CM",
                "dry caugh",
                "Dr Sukrutha",
                "12-11-2022 10:00am",
                "Approved", "cancel"
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ApprovingAdapter(ArrayList<Approving>())

        recyclerView.adapter = ApprovingAdapter(ApprovingList)

        return view
    }
}
package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.RequestAdapter
import com.poc.onlinehospitalappointment.data.Request

class RequestFragement:Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var RequestList: ArrayList<Request>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.request_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view3)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        RequestList = ArrayList()
        RequestList.add(Request(
            "Roshan Reddi",
            "fever,chills",
            "Dr Johnsmith","10-10-2022 11:00 am","Pending","Approve"
        ))

        RequestList.add(Request(
            "Rashmika mandanna",
            "tiredness,headache",
            "Dr Rishab Shetty","10-11-2022 12:00pm",
            "Pending","Approve")
        )
        RequestList.add(Request(
            "Sudeep reddi",
            "headache",
            "Dr Prashanti",
            "10-11-2022 10:00am","Pending","Approve"
        ))
        RequestList.add(
            Request(
                "Ruchitha shetty",
                "vomitting,diarrhea",
                "Dr Rakshith Shetty","12-11-2022 4:00pm","Pending","Approve")
        )
        RequestList.add(
            Request(
                "Prashanth PM",
                "watery red eyes",
                "Dr Veeresh","13-11-2022 1:00pm","Pending","Approve"
            )
        )
        RequestList.add(
            Request(
                "Prakruthi Reddi",
                "dry caugh",
                "Dr Mahesh patil","13-11-2022 10:00am","Pending"
                ,"Approve")
        )
        RequestList.add(Request(
            "Abhinav CM",
            "dry caugh",
            "Dr Sukrutha","12-11-2022 10:00am","Pending","Approve"
        ))

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RequestAdapter(ArrayList<Request>())
        recyclerView.adapter = RequestAdapter(RequestList)
        return view
    }

}
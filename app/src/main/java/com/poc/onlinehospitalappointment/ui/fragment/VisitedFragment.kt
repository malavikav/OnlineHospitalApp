package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.VisitedAdapter
import com.poc.onlinehospitalappointment.data.Visited

class VisitedFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var VisitedList: ArrayList<Visited>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        VisitedList = ArrayList()
        VisitedList.add(Visited(
            " Praveen reddi",
            "02-04-2021",

            "Visited"))

        VisitedList.add(Visited(
            "Suma CM",
            "02-04-2020",
            "Visited"
        ))
        VisitedList.add(
            Visited(
                " Rakshit Shetty",
                "03-05-2022",
                "Visited")
        )

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = VisitedAdapter(ArrayList<Visited>())

        recyclerView.adapter = VisitedAdapter(VisitedList)
        return view
    }

}
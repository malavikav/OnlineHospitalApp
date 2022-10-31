package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.PatientAdapter
import com.poc.onlinehospitalappointment.data.Patient

class TreatmentHistoryFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var PatientList: ArrayList<Patient>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.treatment_history_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        PatientList = ArrayList()
        PatientList.add(
            Patient(
            "Dr Johnsmith",
            "02-04-2021",
            "fever,chills",
        )
        )

        PatientList.add(
            Patient(
            "Dr Rishab Shetty",
            "03-02-2022",
            "tiredness,headache",
        )
        )
        PatientList.add(
            Patient(
            "Dr Johnsmith",
            "02-04-2020",
            "headache",
        )
        )
        PatientList.add(
            Patient(
                "Dr Raksit Shetty",
                "03-05-2022",
                "vomitting,diarrhea", )
        )
        PatientList.add(
            Patient(
                "Dr Prashanti",
                "09-09-2022",
                "watery red eyes",
            )
        )
        PatientList.add(
            Patient(
                "Dr Veeresh",
                "08-10-202",
                "dey caugh",
            )
        )
        PatientList.add(
            Patient(
            "Dr Johnsmith",
            "02-04-2018",
            "fever",
        )
        )
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PatientAdapter(ArrayList<Patient>())

        recyclerView.adapter = PatientAdapter(PatientList)
        return view
    }
}

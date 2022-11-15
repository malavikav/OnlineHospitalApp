package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.PatientAdapter
import com.poc.onlinehospitalappointment.base.BaseFragment
import com.poc.onlinehospitalappointment.data.Patient

class TreatmentHistoryFragment: BaseFragment() {
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
            "Dr Malavika V",
            "02-04-2021",
            "fever,chills",R.drawable.img_3,
        )
        )

        PatientList.add(
            Patient(
            "Dr Mrunal",
            "03-02-2022",
            "tiredness,headache",R.drawable.img_2
        )
        )
        PatientList.add(
            Patient(
            "Dr Pavan",
            "02-04-2020",
            "headache",R.drawable.img_4
        )
        )
        PatientList.add(
            Patient(
                "Dr Vaibhavi",
                "03-05-2022",
                "vomitting,diarrhea",R.drawable.img_3 )
        )
        PatientList.add(
            Patient(
                "Dr Arun",
                "09-09-2022",
                "watery red eyes",R.drawable.img_1
            )
        )
        PatientList.add(
            Patient(
                "Dr Veeresh",
                "08-10-202",
                "dey caugh",R.drawable.img_4
            )
        )
        PatientList.add(
            Patient(
            "Dr Indraja",
            "02-04-2018",
            "fever",R.drawable.img_3
        )
        )
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PatientAdapter(ArrayList<Patient>())

        recyclerView.adapter = PatientAdapter(PatientList)
        return view
    }
}

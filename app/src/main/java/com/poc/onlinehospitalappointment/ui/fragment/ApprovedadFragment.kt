package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.ApprovedadAdapter
import com.poc.onlinehospitalappointment.data.Approvedad

class ApprovedadFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var ApprovedPatientList: ArrayList<Approvedad>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.approvedad_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view5)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        ApprovedPatientList = ArrayList()
        ApprovedPatientList.add(
            Approvedad(
                " Praveen reddi",
                "02-04-2021",
                "fever,chills",
                "Visit"
            )
        )

        ApprovedPatientList.add(
            Approvedad(
                "Suma CM",
                "02-04-2020",
                "headache", "Visit"
            )
        )
        ApprovedPatientList.add(
            Approvedad(
                " Rakshit Shetty",
                "03-05-2022",
                "vomitting,diarrhea", "Visit"
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ApprovedadAdapter(ArrayList<Approvedad>())

        recyclerView.adapter = ApprovedadAdapter(ApprovedPatientList)

        return view
    }
}

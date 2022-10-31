package com.poc.onlinehospitalappointment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.DoctorAdapter
import com.poc.onlinehospitalappointment.data.Doctor

class AppointmentFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var DoctorList: ArrayList<Doctor>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        DoctorList = ArrayList()
        DoctorList.add(
            Doctor(R.drawable.img,
                "Dr Johnsmith",
                "Cardiologist",
                "A cardiologist is a physician who's an expert in the care of your heart and blood vessels.",
                "MBBS",
                "Book")
        )
        DoctorList.add(
            Doctor(R.drawable.img_1,
                "Dr Rishab Shetty",
                "Psychologist",
                "A psychologist is a professional who practices psychology and studies mental states, perceptual, cognitive, emotional, and social processes and behavior.",
                "MBBS",
                "Book")
        )
        DoctorList.add(
            Doctor(R.drawable.img_2,
                "Dr Raksit Shetty",
                "Dentist",
                "A dentist, also known as a dental surgeon, is a health care professional who specializes in dentistry (the diagnosis, prevention, management, and treatment of diseases and conditions of the oral cavity",
                "BDS",
                "Book")
        )
        DoctorList.add(
            Doctor(R.drawable.img_3,
                "Dr Prashanti",
                "Surgeon",
                "In modern medicine, a surgeon is a medical professional who performs surgery.",
                "MBBS,MD",
                "Book")
        )
        DoctorList.add(
            Doctor(R.drawable.img_4,
                "Dr Veeresh",
                "ENT",
                "An otolaryngologist or an ENT doctor is a specialist who diagnoses and treats diseases of ear, nose, and throat.",
                "MBBS",
                "Book")
        )

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = DoctorAdapter(ArrayList<Doctor>())
//
        recyclerView.adapter = DoctorAdapter(DoctorList)

        return view
    }
}
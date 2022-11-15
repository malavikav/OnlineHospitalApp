package com.poc.onlinehospitalappointment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.poc.onlinehospitalappointment.repository.AppointmentRepository
import com.poc.onlinehospitalappointment.repository.AuthRepository
import com.poc.onlinehospitalappointment.repository.AuthRepository.Companion.getInstance

class AppointmentViewModel(val context: Context):ViewModel (){

    fun bookAppointmentCreate( patientName : String,
                               patientId : String,
                               doctorName : String,
                               doctorId : String,
                               date : String,
                               time : String,
                               status : String,
                               description:String) {
        return AppointmentRepository.getInstance(context)
            .book(patientName, patientId, doctorName, doctorId, date, time, status,description)
    }
}
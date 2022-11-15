package com.poc.onlinehospitalappointment.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.data.Appointment
import com.poc.onlinehospitalappointment.data.User
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass

class AppointmentRepository {
    var sharedPreferance: SharedPreferenceClass

    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Appointment")
    init {
        sharedPreferance = SharedPreferenceClass
    }
    companion object {
        private var bookRepository: AppointmentRepository? = null
        private var context: Context? = null

        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): AppointmentRepository {
            this.context = context

            if (bookRepository == null) bookRepository = AppointmentRepository()
            return bookRepository!!
        }
    }

    fun book(
        patientName: String,
        patientId: String,
        doctorName: String,
        doctorId: String,
        date: String,
        time: String,
        status: String,
        description:String
    ) {
        val userId = sharedPreferance.read(Constants.USER_ID, "")!!
        val appointment = Appointment(
            patientName = patientName,
            patientId = patientId,
            doctorName = doctorName,
            doctorId = doctorId,
            date = date,
            time = time,
            status = status,
            description = description
        )

        databaseReference.child(userId).setValue(appointment).addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful) {

                } else {
                    Log.e("not Successful", "=====>")

                }
            })
    }
}
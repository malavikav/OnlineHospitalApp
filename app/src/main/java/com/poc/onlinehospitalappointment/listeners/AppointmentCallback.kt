package com.poc.onlinehospitalappointment.listeners

import com.poc.onlinehospitalappointment.data.Doctor

interface AppointmentCallback
{
    fun onBookClick(doctorData: Doctor)
}
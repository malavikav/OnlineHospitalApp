package com.poc.onlinehospitalappointment.data

data class Request(val pImage: Int? = null,
                   val patientName : String? = null,
                   val patientId : String? = null,
                   val doctorName : String? = null,
                   val doctorId : String? = null,
                   val date : String? = null,
                   val time : String? = null,
                   val status : String? = null,
                   val description :String?=null)

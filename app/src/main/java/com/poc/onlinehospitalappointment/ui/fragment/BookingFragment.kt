package com.poc.onlinehospitalappointment.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.base.BaseFragment
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.databinding.FragmentBookingBinding
import com.poc.onlinehospitalappointment.repository.AppointmentFactory
import com.poc.onlinehospitalappointment.ui.activity.Booked
import com.poc.onlinehospitalappointment.viewmodel.AppointmentViewModel
import java.util.*
private const val DOCTOR_DATA = "param2"
class BookingFragment(private val doctorData: Doctor) : BaseFragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private var doctordetailslist: Bundle? = null
    private lateinit var binding: FragmentBookingBinding
    private lateinit var appointmentViewModel: AppointmentViewModel

    companion object {
        @JvmStatic
        fun newInstance(param2: Doctor) =
            BookingFragment(param2).apply {
                arguments = Bundle().apply {

                    putString(DOCTOR_DATA, param2.toString())


                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            doctordetailslist = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBookingBinding.inflate(inflater, container, false)

        appointmentViewModel = ViewModelProvider(this, AppointmentFactory(requireContext()))[AppointmentViewModel::class.java]


//        date = view.findViewById(R.id.date)
//        time = view.findViewById(R.id.time)
//        book = view.findViewById(R.id.book)
//        desc = view.findViewById(R.id.p_desc)

        binding.book.setOnClickListener {
            val description = binding.pDesc.text.toString()
            val datee = binding.date.text.toString()
            val timee = binding.time.text.toString()
            if (description.isEmpty()) {
                binding.pDesc.error = " Description is empty"
                return@setOnClickListener
            } else if (datee.isEmpty()) {
                binding.date.error = "Invalid Date"
                return@setOnClickListener
            } else if (timee.isEmpty()) {
                binding.time.error = "Invalid Time"
                return@setOnClickListener
            }
            else{observeAppointment(sharedPreferance.read(Constants.USER_FNAME,"")!!,
                sharedPreferance.read(Constants.USER_ID,"")!! ,
                doctorData.DName,
                doctorData.DId,
                binding.date.text.toString(),
                binding.time.text.toString(),
                "pending",binding.pDesc.text.toString())}


            //book.setOnClickListener {
//            val builder = AlertDialog.Builder(requireContext())
//            val alertDialog: AlertDialog = builder.create()
//            builder.setMessage("Do you want to book appointment?")
//            builder.setPositiveButton("Yes") { dialogInterface, which ->
//
//                Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
//                observeAppointment("PATIENT NAME",
//                    "PATIENT ID ",
//                    "DOCTOR NAME",
//                    "DOCTOR ID",
//                    "DATE",
//                    "TIME",
//                    "pending")
//
//
//            }
//
//            builder.setNegativeButton("No") { dialogInterface, which ->
//                Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
//            alertDialog.dismiss()
//            }
//
//            alertDialog.setCancelable(false)
//            alertDialog.show()

        }
        binding.date.setOnClickListener(this)
        binding.time.setOnClickListener(this)
        return binding.root
    }

    private fun observeAppointment(
        patientName: String,
        patientId: String,
        doctorName: String,
        doctorId: String,
        date: String,
        time: String,
        status: String,
        description:String
    ) {
        appointmentViewModel?.bookAppointmentCreate(
            patientName ,
            patientId ,
            doctorName ,
            doctorId ,
            date ,
            time ,
            status,
        description)
        showAlertDialogButtonClicked()


    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        val calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.datePicker.minDate = Date().time
        datePickerDialog.show()

        val cal = java.util.Calendar.getInstance()
        val hour: Int = cal.get(java.util.Calendar.HOUR)
        val minute: Int = cal.get(java.util.Calendar.MINUTE)
        val timePicker = TimePickerDialog(requireContext(), this, hour, minute, false)
        timePicker.show()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val value = ("${month + 1} / $dayOfMonth / $year").toString()
        binding.date.setText(value)
        // time.setText(value)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val myTime = " $hourOfDay : $minute"
        binding.time.setText(myTime)
    }

    fun showAlertDialogButtonClicked() {

        // Create an alert builder
        val builder = AlertDialog.Builder(requireContext())


        // set the custom layout
        val customLayout: View = layoutInflater
            .inflate(
                R.layout.activity_booked,
                null
            )
        builder.setView(customLayout)

        // add a button


        // create and show
        // the alert dialog
        val dialog = builder.create()
        dialog.show()

    }
}










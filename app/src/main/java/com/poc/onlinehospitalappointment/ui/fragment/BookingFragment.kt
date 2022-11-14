package com.poc.onlinehospitalappointment.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.base.BaseFragment
import com.poc.onlinehospitalappointment.data.Doctor
import java.util.*

private const val DOCTOR_DATA = "param2"
class BookingFragment(private val doctorData:Doctor) : BaseFragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var date: EditText
    lateinit var time: EditText
    lateinit var book: TextView
    lateinit var datePicker: Button
    lateinit var timePicker: Button
    lateinit var desc: EditText
    private var doctordetailslist: Bundle? = null

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        date = view.findViewById(R.id.date)
        time = view.findViewById(R.id.time)
        book = view.findViewById(R.id.book)
        desc = view.findViewById(R.id.p_desc)

        book.setOnClickListener {
            val description = desc.text.toString()
            val datee = date.text.toString()
            val timee = time.text.toString()
            if (description.isEmpty()) {
                desc.error = " Description is empty"
                return@setOnClickListener
            } else if (datee.isEmpty()) {
                date.error = "Invalid Date"
                return@setOnClickListener
            } else if (timee.isEmpty()) {
                time.error = "Invalid Time"
                return@setOnClickListener
            }


            //book.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Do you want to book appointment?")
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()

            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            book.setOnClickListener {
                showAlertDialogButtonClicked()
            }
        }
        date.setOnClickListener(this)
        time.setOnClickListener(this)
        return view
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
        date.setText(value)
        // time.setText(value)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val myTime = " $hourOfDay : $minute"
        time.setText(myTime)
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










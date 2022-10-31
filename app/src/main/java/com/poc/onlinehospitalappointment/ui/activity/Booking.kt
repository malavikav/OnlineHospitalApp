package com.poc.onlinehospitalappointment.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.poc.onlinehospitalappointment.R


class Booking : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var date: EditText
    lateinit var time: EditText
    lateinit var book: Button
    lateinit var datePicker: Button
    lateinit var timePicker: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar!!.title = "Booking"
        actionBar.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_booking)
        date = findViewById(R.id.date)
        time = findViewById(R.id.time)
        book = findViewById(R.id.book)


        book.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to book appointment?")
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()

            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            book.setOnClickListener {
                //val intent = Intent(this, Booked::class.java)
                //
                // startActivity(intent)
                showAlertDialogButtonClicked()
            }
        }


//       book.setOnClickListener {
//            val intent = Intent(this, Booked::class.java)
//            startActivity(intent)
//       }

        date.setOnClickListener(this)
        time.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        val calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()

        val cal = java.util.Calendar.getInstance()
        val hour: Int = cal.get(java.util.Calendar.HOUR)
        val minute: Int = cal.get(java.util.Calendar.MINUTE)
        val timePicker = TimePickerDialog(this, this, hour, minute, false)
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
        val builder = AlertDialog.Builder(this)


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









package com.poc.onlinehospitalappointment.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.adapter.CustomDropDownAdapter
import com.poc.onlinehospitalappointment.base.BaseActivity
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.databinding.ActivityUserSignUpBinding
import com.poc.onlinehospitalappointment.listeners.RegistrationActivityCallback
import com.poc.onlinehospitalappointment.repository.Factory
import com.poc.onlinehospitalappointment.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_user_sign_up.*

class UserSignUp : BaseActivity(), RegistrationActivityCallback {

    val designation = arrayOf("Doctor", "Patient", "Receptionist")

    private lateinit var activityRegistrationBinding: ActivityUserSignUpBinding
    private lateinit var type: String
    private var authViewModel: AuthViewModel? = null
    var spinnerItems: ArrayList<String>? = null
    var selectedOption: String? = null
    var selectedPostion: Int? = null
      var genderOption:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_sign_up)
        readSpinnerData()
        activityRegistrationBinding.registrationActivityCallback = this
        authViewModel = ViewModelProvider(this, Factory(this))[AuthViewModel::class.java]
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

//        radio_group.setOnCheckedChangeListener { group, checkedId ->
//            if (checkedId == R.id.male_radioBtn) {
//                Toast.makeText(this,male_radioBtn.text.toString(),Toast.LENGTH_SHORT).show()
//            }
//            if (checkedId == R.id.female_radioBtn) {
//                Toast.makeText(this,female_radioBtn.text.toString(),Toast.LENGTH_SHORT).show()
//            }
//
//        }

        activityRegistrationBinding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(pO: Editable?) {

            }


            override fun beforeTextChanged(pO: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(pO: CharSequence?, p1: Int, p2: Int, p3: Int) {


                if (android.util.Patterns.EMAIL_ADDRESS.matcher(activityRegistrationBinding.emailEt.text.toString())
                        .matches()
                )
                    activityRegistrationBinding.SignUpBtn.isEnabled = true
                else {
                    activityRegistrationBinding.SignUpBtn.isEnabled = false
                    activityRegistrationBinding.emailEt.error = "Invalid Email"
                }


            }


        })
        val customDropDownAdapter = spinnerItems?.let {
            CustomDropDownAdapter(this,
                it)
        }
        activityRegistrationBinding.spinner.adapter = customDropDownAdapter

        activityRegistrationBinding.LoginTV.setOnClickListener()
        {
            startActivity(Intent(this@UserSignUp, UserLogin::class.java))
        }
        activityRegistrationBinding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long,
            ) {

                selectedOption = spinnerItems?.get(position)
                selectedPostion = position

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


    }


    private fun observeRegister(
        email: String,
        password: String,
        selectedOption: String,
        firstname: String,
        lastname: String,
        age: String,
        dob: String,
        gender: String,
        number: String,
        enumber: String,
        userImage:String
    ) {
        activityRegistrationBinding.loader.visibility = View.VISIBLE
        authViewModel?.registerUser(email,
            password,
            selectedOption,
            firstname,
            lastname,
            age,
            dob,
            gender,
            number,
            enumber,
            userImage)
            ?.observe(this, Observer {
                activityRegistrationBinding!!.loader.visibility = View.GONE
                if (it !== null && it.isSuccessful) {


                    var intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(Constants.USER_TYPE, selectedOption)
                    startActivity(intent)
                    finish()

                } else {
                    showError()
                }

            })
    }

    override fun onRegisterClick(view: View) {

        val password = activityRegistrationBinding.passwordEt.text.toString()
        val confirmPassword = activityRegistrationBinding.confirmPw.text.toString()


        if (activityRegistrationBinding.fname.text.toString().isNullOrBlank()) {
            activityRegistrationBinding.fname.error = "First Name Required"
            return
        }
        if (activityRegistrationBinding.lname.text.toString().isNullOrBlank()) {
            activityRegistrationBinding.lname.error = "Last Name Required"
            return
        }
        if (activityRegistrationBinding.emailEt.text.toString().isNullOrBlank()) {
            activityRegistrationBinding.emailEt.error = "Email Required"
            return
        }
        if (activityRegistrationBinding.passwordEt.text.toString().isNullOrBlank()) {
            activityRegistrationBinding.passwordEt.error = "Password Required"
            return
        }
        if (activityRegistrationBinding.confirmPw.text.toString().isNullOrBlank()) {
            activityRegistrationBinding.confirmPw.error = "Confirm Password Required"
            return
        }
        if (confirmPassword != password) {
            activityRegistrationBinding.confirmPw.error = " password should match"
            return
        }
        if (genderOption.isNullOrBlank()) {
            Toast.makeText(this, "Please select the Gender", Toast.LENGTH_LONG).show()
            return
        }
        if (selectedPostion == 0) {
            Toast.makeText(this, "Please select the Designation", Toast.LENGTH_LONG).show()
            return
        }
        activityRegistrationBinding.loader.visibility = View.VISIBLE
        observeRegister(
            activityRegistrationBinding.emailEt.text.toString(),
            activityRegistrationBinding.passwordEt.text.toString(),
            selectedOption.toString(),
            activityRegistrationBinding.fname.text.toString(),
            activityRegistrationBinding.lname.text.toString(),
            "",
            "",
            genderOption!!,
            "",
            "",
            ""
        )


    }

    override fun selectOption(view: View) {
         if (view.id == R.id.male_radioBtn) {
             genderOption ="Male"
     } else if (view.id == R.id.female_radioBtn) {
             genderOption ="Female"
     }
    }

    private fun readSpinnerData() {
        spinnerItems = ArrayList<String>()
        spinnerItems!!.add("Choose your Designation")
        spinnerItems!!.add("Doctor")
        spinnerItems!!.add("Patient")
        spinnerItems!!.add("Receptionist")
    }

    private fun showError() {
        activityRegistrationBinding.loader.visibility = View.GONE
        Toast.makeText(
            applicationContext,
            "Signup Failed, please try again",
            Toast.LENGTH_SHORT
        ).show()
    }

}




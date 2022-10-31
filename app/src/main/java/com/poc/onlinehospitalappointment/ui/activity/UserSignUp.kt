package com.poc.onlinehospitalappointment.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.base.BaseActivity
import com.poc.onlinehospitalappointment.databinding.ActivityUserSignUpBinding
import com.poc.onlinehospitalappointment.listeners.RegistrationActivityCallback
import com.poc.onlinehospitalappointment.repository.Factory
import com.poc.onlinehospitalappointment.viewmodel.AuthViewModel

class UserSignUp : BaseActivity(), RegistrationActivityCallback {

    val designation = arrayOf("Doctor", "Patient", "Receptionist")

    private lateinit var activityRegistrationBinding: ActivityUserSignUpBinding
    private lateinit var type: String
    private var authViewModel: AuthViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRegistrationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_sign_up)
        activityRegistrationBinding.registrationActivityCallback = this
        authViewModel = ViewModelProvider(this, Factory(this))[AuthViewModel::class.java]


        /*  val actionBar: ActionBar? = supportActionBar
          actionBar?.hide()*/


        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, designation)
        activityRegistrationBinding.designtionSpinner.adapter = arrayAdapter
        activityRegistrationBinding.designtionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(
                        applicationContext,
                        "selected Designation is = " + designation[p2],
                        Toast.LENGTH_SHORT
                    ).show()

                    type = activityRegistrationBinding.designtionSpinner.selectedItem.toString()

                    /* if (p2 == 1) {
                         binding.ageEt.visibility = View.VISIBLE
                     } else {
                         binding.ageEt.visibility = View.GONE
                     }*/

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }


            }


        activityRegistrationBinding.LoginTV.setOnClickListener()
        {
            startActivity(Intent(this@UserSignUp, UserLogin::class.java))
        }


       /* activityRegistrationBinding.SignUpBtn.setOnClickListener {
            val email = activityRegistrationBinding.emailEt.text.toString()
            val password = activityRegistrationBinding.passwordEt.text.toString()
            val confirmPassword = activityRegistrationBinding.confirmPwEt.text.toString()
            val username = activityRegistrationBinding.usernameEt.text.toString()


            if (password == confirmPassword) {

                activityRegistrationBinding.emailEt.text.clear()
                activityRegistrationBinding.passwordEt.text.clear()
                activityRegistrationBinding.confirmPwEt.text.clear()
                activityRegistrationBinding.usernameEt.text.clear()

                startActivity(Intent(this, UserLogin::class.java))
                finish()

            } else {
                Toast.makeText(this, "Insertion Error", Toast.LENGTH_LONG).show()
            }


        }
*/
    }

    private fun observeRegister(
        email: String,
        password: String,
        firstname: String,
        lastname: String,
    ) {
        authViewModel?.registerUser(email, password, type, firstname, lastname)
            ?.observe(this, Observer {
                if (it != null) {
                    Log.e("registration Success", "================>")
                    Toast.makeText(this, "registration Success", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.e("registration failed", "================>")

                    Toast.makeText(
                        this,
                        "Login Failed, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    override fun onRegisterClick(view: View) {
        observeRegister(
            activityRegistrationBinding.emailEt.text.toString(),
            activityRegistrationBinding.passwordEt.text.toString(),
            activityRegistrationBinding.usernameEt.text.toString(),
            activityRegistrationBinding.usernameEt.text.toString()
        )
    }

    override fun selectOption(view: View) {
        /* if (view.id == R.id.Doctor_radioBtn) {
             selectedOption = 1
         } else if (view.id == R.id.Patient_radioBtn) {
             selectedOption = 2
         } else if (view.id == R.id.Content_radioBtn) {
             selectedOption = 3
         }*/
    }
}


package com.poc.onlinehospitalappointment.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.base.BaseActivity
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.databinding.ActivityUserLoginBinding
import com.poc.onlinehospitalappointment.listeners.LoginActivityCallback
import com.poc.onlinehospitalappointment.repository.Factory
import com.poc.onlinehospitalappointment.viewmodel.AuthViewModel

class UserLogin : BaseActivity(), LoginActivityCallback {


    private var activityLoginBinding: ActivityUserLoginBinding? = null
    private var authViewModel: AuthViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_login)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        activityLoginBinding?.loginActivityCallback = this
        authViewModel = ViewModelProvider(this, Factory(this))[AuthViewModel::class.java]
    }


    override fun onLoginClick(view: View) {
        if (activityLoginBinding?.emailEt?.text.toString().isNullOrBlank()) {
            activityLoginBinding?.emailEt?.error = "Email Required"
            return
        }
        if (activityLoginBinding?.passwordEt?.text.toString().isNullOrBlank()) {
            activityLoginBinding?.passwordEt?.error = "Password Required"

            return
        }

        activityLoginBinding!!.loader.visibility = View.VISIBLE
        observeLogin(
            activityLoginBinding?.emailEt?.text.toString(),
            activityLoginBinding?.passwordEt?.text.toString()
        )
    }

    override fun onSignUpClick(view: View) {
        var intent = Intent(this, UserSignUp::class.java)
        startActivity(intent)
    }

    private fun observeLogin(email: String, password: String) {
        authViewModel?.loginUser(email, password)?.observe(this, Observer {
            activityLoginBinding!!.loader.visibility = View.GONE
            if (it != null) {
                activityLoginBinding!!.loader.visibility = View.GONE
                sharedPreferance.write(Constants.IS_USER_LOGGED, true)
                sharedPreferance.write(Constants.USER_FNAME, it.fname)
                sharedPreferance.write(Constants.USER_LNAME, it.lname)
                sharedPreferance.write(Constants.USER_TYPE, it.type)
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constants.USER_TYPE, it.type)
                startActivity(intent)
                finish()

            } else {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(
            applicationContext,
            "Login Failed, please try again",
            Toast.LENGTH_SHORT
        ).show()
    }


}



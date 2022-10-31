package com.poc.onlinehospitalappointment.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass

open class BaseActivity : AppCompatActivity() {

    lateinit var sharedPreferance: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        sharedPreferance = SharedPreferenceClass
        SharedPreferenceClass.init(applicationContext)!!
    }

}
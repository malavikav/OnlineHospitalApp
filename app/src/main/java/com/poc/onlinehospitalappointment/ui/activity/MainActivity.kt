package com.poc.onlinehospitalappointment.ui.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.base.BaseActivity
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.databinding.ActivityMainBinding
import com.poc.onlinehospitalappointment.ui.fragment.*

class MainActivity : BaseActivity() {
    lateinit var bottomNav: BottomNavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var userType: String
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        bottomNav = findViewById(R.id.bottomNav)
        userType = sharedPreferance.read(Constants.USER_TYPE, "").toString()
        if (userType == Constants.DOCTOR) {
            activityMainBinding.bottomNav.inflateMenu(R.menu.doctor_nav_menu)
            loadFragment(ApprovedadFragment())
        } else if (userType == Constants.PATIENT) {
            activityMainBinding.bottomNav.inflateMenu(R.menu.patient_nav_menu)
            loadFragment(AppointmentFragment())


        } else if (userType == Constants.RECEPTIONIST) {
            activityMainBinding.bottomNav.inflateMenu(R.menu.receptionist_nav_menu)
            loadFragment(RequestFragement())
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ad -> {
                    when (userType) {
                        "Patient" -> {
                            loadFragment(AppointmentFragment())
                        }
                        "Receptionist" -> {
                            loadFragment(RequestFragement())
                        }
                        else -> {
                            loadFragment(ApprovedadFragment())
                        }
                    }
                    true
                }
                R.id.th -> {
                    when (userType) {
                        "Patient" -> {
                            loadFragment(TreatmentHistoryFragment())
                        }
                        "Receptionist" -> {
                            loadFragment(ApprovingFragment())
                        }
                        else -> {
                            loadFragment(VisitedFragment())
                        }
                    }
                    true
                }
                R.id.up -> {
                    loadFragment(UserFragment())
                    true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}
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
    lateinit var type: String
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        bottomNav = findViewById(R.id.bottomNav)

        val intent = intent
        type = intent.getStringExtra(Constants.USER_TYPE).toString()
        when (type) {
            "Patient" -> {
                loadFragment(AppointmentFragment())
                bottomNav.menu.getItem(0).title = "Appointment Details"
                bottomNav.menu.getItem(1).title = "Treatment History"
                bottomNav.menu.getItem(2).title = "User Profile"

            }
            "Admin" -> {
                loadFragment(RequestFragement())
                bottomNav.menu.getItem(0).title = "Request List"
                bottomNav.menu.getItem(1).title = "Approved List"
                bottomNav.menu.getItem(2).title = "User Profile"
            }
            else -> {
                loadFragment(ApprovedadFragment())
                bottomNav.menu.getItem(0).title = "Approved List"
                bottomNav.menu.getItem(1).title = "Visited List"
                bottomNav.menu.getItem(2).title = "User Profile"
            }
        }
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ad -> {
                    when (type) {
                        "Patient" -> {
                            loadFragment(AppointmentFragment())
                            it.title = "Appointment Details"

                        }
                        "Admin" -> {
                            loadFragment(RequestFragement())
                            it.title = "Request List"

                        }
                        else -> {
                            loadFragment(ApprovedadFragment())
                            it.title = "Approved List"

                        }
                    }
                    true
                }
                R.id.th -> {
                    when (type) {
                        "Patient" -> {
                            loadFragment(TreatmentHistoryFragment())
                            it.title = "Treatment History"

                        }
                        "Admin" -> {
                            loadFragment(ApprovingFragment())
                            it.title = "Approved List"
                        }
                        else -> {
                            loadFragment(VisitedFragment())
                            it.title = "Visited List"
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
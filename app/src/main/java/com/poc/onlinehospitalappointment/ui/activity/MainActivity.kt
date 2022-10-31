package com.poc.onlinehospitalappointment.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.poc.onlinehospitalappointment.R
import com.poc.onlinehospitalappointment.ui.fragment.*

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var type: String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav)

        val intent = intent
        type = intent.getStringExtra("Designation").toString()
        Log.e("designation", "" + type)
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
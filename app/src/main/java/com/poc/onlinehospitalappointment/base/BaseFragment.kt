package com.poc.onlinehospitalappointment.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass

open class BaseFragment : Fragment() {
    lateinit var sharedPreferance: SharedPreferenceClass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferance = SharedPreferenceClass
        SharedPreferenceClass.init(context)!!
    }
}

package com.poc.onlinehospitalappointment.repository

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poc.onlinehospitalappointment.viewmodel.AuthViewModel

class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(context) as T
    }
}
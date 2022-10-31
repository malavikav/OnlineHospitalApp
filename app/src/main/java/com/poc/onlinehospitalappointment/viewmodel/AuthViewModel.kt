package com.poc.onlinehospitalappointment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.poc.onlinehospitalappointment.data.User
import com.poc.onlinehospitalappointment.repository.AuthRepository

class AuthViewModel(val context: Context) : ViewModel() {

    /*fun loginUser(email: String, password: String): LiveData<Task<AuthResult>> {
        return AuthRepository.getInstance(context).login(email, password)
    }

   fun registerUser(email: String, password: String, confirmPass: String ): LiveData<Task<AuthResult>> {
        return AuthRepository.getInstance(context).register(email, password, confirmPass)
    }*/
    fun loginUser(email: String, password: String): LiveData<User> {
        return AuthRepository.getInstance(context).login(email, password)
    }

    fun registerUser(
        email: String,
        password: String,
        selectedOption: String,
        firstname: String,
        lastname: String,
    ): LiveData<Task<AuthResult>> {
        return AuthRepository.getInstance(context)
            .register(email, password, selectedOption, firstname, lastname)
    }

    /* fun nurseList(firstname:String,  lastname:String, nselectedOption:Int,
                    mobileNo: String , email:String, address:String, experience: String):LiveData<Task<AuthResult>> {
         return AuthRepository.getInstance(context)
             .addNurse(firstname, lastname, nselectedOption,mobileNo,email, address,experience)
     }*/

}



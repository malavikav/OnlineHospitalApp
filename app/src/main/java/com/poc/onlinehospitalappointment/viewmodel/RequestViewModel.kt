package com.poc.onlinehospitalappointment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Request
import com.poc.onlinehospitalappointment.repository.RequestRepository

class RequestViewModel   : ViewModel() {

    private val repository : RequestRepository
    private val _allUsers = MutableLiveData<List<Request>>()
    val allUsers : LiveData<List<Request>> = _allUsers


    init {

        repository = RequestRepository().getInstance()
        repository.loadUsers(_allUsers)

    }

}
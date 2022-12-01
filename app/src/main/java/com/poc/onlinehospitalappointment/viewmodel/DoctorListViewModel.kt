package com.poc.onlinehospitalappointment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.data.DoctorList
import com.poc.onlinehospitalappointment.repository.DoctorListRepository

class DoctorListViewModel  : ViewModel() {

    private val repository : DoctorListRepository = DoctorListRepository().getInstance()
    private val _allUsers = MutableLiveData<List<DoctorList>>()
    val allUsers : LiveData<List<DoctorList>> = _allUsers


    init {

        repository.loadUsers(_allUsers)

    }

}
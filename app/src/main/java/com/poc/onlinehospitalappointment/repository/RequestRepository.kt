package com.poc.onlinehospitalappointment.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Request

class RequestRepository  {private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Appointment")

    @Volatile private var INSTANCE : RequestRepository ?= null

    fun getInstance() : RequestRepository{
        return INSTANCE ?: synchronized(this){

            val instance = RequestRepository()
            INSTANCE = instance
            instance
        }


    }


    fun loadUsers(userList : MutableLiveData<List<Request>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<Request> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(Request::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (e : Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }

}
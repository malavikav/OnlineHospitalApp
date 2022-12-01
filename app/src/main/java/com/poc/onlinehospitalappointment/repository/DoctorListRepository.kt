package com.poc.onlinehospitalappointment.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.poc.onlinehospitalappointment.data.Approving
import com.poc.onlinehospitalappointment.data.Doctor
import com.poc.onlinehospitalappointment.data.DoctorList

class DoctorListRepository {private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

    @Volatile private var INSTANCE : DoctorListRepository?= null

    fun getInstance() : DoctorListRepository{
        return INSTANCE ?: synchronized(this){

            val instance = DoctorListRepository()
            INSTANCE = instance
            instance
        }


    }


    fun loadUsers(userList : MutableLiveData<List<DoctorList>>){

        databaseReference.orderByChild("type").equalTo("Doctor").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<DoctorList> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(DoctorList::class.java)!!

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
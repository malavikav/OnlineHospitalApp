package com.poc.onlinehospitalappointment.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.data.User
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass

class AuthRepository {

    lateinit var sharedPreferance: SharedPreferenceClass

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    init {
        sharedPreferance = SharedPreferenceClass
    }
    companion object {
        private var loginRepository: AuthRepository? = null
        private var context: Context? = null
        private var registerRepository: AuthRepository? = null

        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): AuthRepository {
            this.context = context

            if (loginRepository == null) loginRepository = AuthRepository()
            return loginRepository!!

            if (registerRepository == null) registerRepository = AuthRepository()
            return registerRepository!!
        }
    }

    fun login(email: String, password: String): LiveData<User> {
        val loginData = MutableLiveData<User>()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener { result ->
                val rootRef = FirebaseDatabase.getInstance().reference
                if (auth.currentUser != null) {
                    val userID = auth.currentUser!!.uid
                    val uidRef = rootRef.child(Constants.USER_TABLE).child(userID)
                    uidRef.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val snapshot = task.result
                            val fname = snapshot.child("fname").getValue(String::class.java)
                            val lname = snapshot.child("lname").getValue(String::class.java)
                            val type = snapshot.child("type").getValue(String::class.java)
                            val dob = snapshot.child("dob").getValue(String::class.java)
                            val age = snapshot.child("age").getValue(String::class.java)
                            val gender = snapshot.child("gender").getValue(String::class.java)
                            val number = snapshot.child("number").getValue(String::class.java)
                            val enumber =
                                snapshot.child("emergency number").getValue(String::class.java)
                            Log.e("fname", "===>" + fname)
                            Log.e("lname", "===>" + lname)
                            Log.e("type", "===>" + type)
                            val user = dob?.let {
                                User(
                                    userID, fname, lname, type, email, password,
                                    it, age, gender, number, enumber
                                )
                            }
                            loginData.postValue(user)
                        }
                    }

                }
                else
                {
                    loginData.postValue(null)
                }
            })
        return loginData
    }

    fun register(
        email: String,
        password: String,
        selectedOption: String,
        firstname: String,
        lastname: String,
        aage: String,
        date: String,
        ggender: String,
        nnumber: String,
        eenumber: String,
    ): LiveData<Task<AuthResult>> {
        val registerData = MutableLiveData<Task<AuthResult>>()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener { result ->
                val firebaseUser = auth.currentUser
                val userID = firebaseUser!!.uid
                Log.e("userID", "=====>$userID")
                val user = User(
                    uid = userID,
                    email = email,
                    password = password,
                    type = selectedOption,
                    fname = firstname,
                    lname = lastname,
                    dob = date,
                    age = aage,
                    number = nnumber,
                    enumber = eenumber,
                    gender = ggender
                )
                if (userID != null) {
                    databaseReference.child(userID).setValue(user).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {

                                sharedPreferance.write(Constants.IS_USER_LOGGED, true)
                                sharedPreferance.write(Constants.USER_TYPE, selectedOption)
                                sharedPreferance.write(Constants.USER_FNAME, firstname)
                                sharedPreferance.write(Constants.USER_LNAME, lastname)
                                sharedPreferance.write(Constants.USER_ID, userID)
                                sharedPreferance.write(Constants.USER_EMAIL, email)

                                registerData.postValue(result)
                            } else {
                                Log.e("not Successful", "=====>")

                            }
                        })
                } else {
                    Log.e("No userID", "=====?")

                }
            })

        return registerData
    }

    /* fun addNurse(
         firstname: String, lastname: String, nselectedOption: Int, mobileno: String,
         email: String, address: String, experience: String
     ): LiveData<Task<AuthResult>>{
         val addNurseData = MutableLiveData<Task<AuthResult>>()

         auth.pendingAuthResult?.addOnCompleteListener(
             {result ->
                 val firebaseUser = auth.currentUser
                 val nurseListID = firebaseUser!!.uid
                 val nurseList =NurseList(firstname = firstname,
                     lastname = lastname,
                     type = nselectedOption,
                     email = email,
                     mobileNo = mobileno,
                     experience = experience,
                     address = address)
                 if ()

         }

         )
     }*/


}


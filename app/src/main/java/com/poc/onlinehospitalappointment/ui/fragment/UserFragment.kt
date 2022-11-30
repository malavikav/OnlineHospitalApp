package com.poc.onlinehospitalappointment.ui.fragment

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.poc.onlinehospitalappointment.base.BaseFragment
import com.poc.onlinehospitalappointment.constants.Constants
import com.poc.onlinehospitalappointment.databinding.UserFragmentBinding
import com.poc.onlinehospitalappointment.preferance.SharedPreferenceClass
import com.poc.onlinehospitalappointment.viewmodel.ProfileInfoViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class UserFragment: BaseFragment() {
    //private lateinit var binding: UserFragmentBinding
    private var userFragmentBinding: UserFragmentBinding? = null
    private val binding get() = userFragmentBinding!!
    private lateinit var ImageUri: Uri
    private val IMAGE_REQUEST_CODE = 1
    private lateinit var file_uri: Uri
    lateinit var viewModel: ProfileInfoViewModel


 

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        userFragmentBinding = UserFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProfileInfoViewModel::class.java)
        readdata()

        binding.updateBtn.setOnClickListener {

            val userName = binding.etUsername.text.toString()
            val age = binding.userAge.text.toString()
            val date = binding.date.text.toString()
            val gender = binding.gender.text.toString()
            val number = binding.number.text.toString()
            val enumber = binding.enumber.text.toString()
            val userImage=sharedPreferance.read(Constants.USER_IMAGE,"")!!.toString()
            Log.e("link","image url" +userImage)
            updatedata(userName, age, date, gender, number, enumber, userImage)
        }
        binding.updateImage.setOnClickListener {
            uploadImage()
        }

        binding.pfl.setOnClickListener{

            selectImage()
        }
        return binding.root

        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.user_fragment, container, false)


        //return view
    }

    private fun uploadImage() {

        var imageLink: MutableLiveData<String> = MutableLiveData()

        val progressDialog = ProgressDialog(requireActivity())
        progressDialog.setMessage("uploading Image.......")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val formatter = SimpleDateFormat("yy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = sharedPreferance.read(Constants.USER_ID,"")!!
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName.jpg")
        Log.e("storage reference",""+storageReference)
        storageReference.putFile(file_uri).addOnSuccessListener(
                OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        val imageUrl = it.toString()
                        imageLink.postValue(imageUrl)
                        sharedPreferance.write(Constants.USER_IMAGE,it.toString())
//                        Log.e("imageurl"," image link"+imageUrl)
                      // sharedPreferance.write(Constants.USER_IMAGE, it.toString())
                    }

            binding.pfl.setImageURI(file_uri)
            Log.e("uri",""+file_uri)
            Toast.makeText(requireActivity(),"updated successfully ",Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing)
                progressDialog.dismiss()

                })
            ?.addOnFailureListener {
            Toast.makeText(requireActivity(),"update Failed ",Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing)
                progressDialog.dismiss()

        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null
            && data.data != null
        ) {
            file_uri = data.data!!
            binding.pfl.setImageURI(data?.data)
        }
    }


    private fun updatedata(
        userName: String,
        age: String,
        date: String,
        gender: String,
        number: String,
        enumber: String,
        userImage:String
    ) {

        val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "fname" to userName,
            "age" to age,
            "gender" to gender,
            "dob" to date,
            "number" to number,
            "enumber" to enumber,
            "userImage" to userImage,

        )
        databaseReference.child(sharedPreferance.read(Constants.USER_ID,"")!!).updateChildren(user).addOnSuccessListener{
        Toast.makeText(requireActivity(),"data updated",Toast.LENGTH_LONG).show()


        }.addOnFailureListener{
            Toast.makeText(requireActivity(),"update failed",Toast.LENGTH_LONG).show()



        }

        }


    private fun readdata() {
        val fileName = sharedPreferance.read(Constants.USER_ID,"")!!
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$fileName.jpg")

        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.pfl.setImageBitmap(bitmap)
        }.addOnFailureListener{
            Toast.makeText(requireActivity(),"Failed to load profile picture",Toast.LENGTH_SHORT).show()
        }
        //var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")
       // val uidRef = rootRef.child(Constants.USER_TABLE).child(Constants.USER_ID)
       // val userId = auth.currentUser!!.uid
        databaseReference.child(sharedPreferance.read(Constants.USER_ID,"")!!).get().addOnSuccessListener {
            Log.e("result",""+it)
            if (it.exists()) {


                val username = it.child("fname").value
                Log.e("username",""+it.child("fname"))
                //val demo = it.child(designation.toString()).value
                val email = it.child("email").value
                val designation = it.child("type").value
                val age = it.child("age").value
                val gender = it.child("gender").value
                val dob = it.child("dob").value
                val number = it.child("number").value
                val enumber = it.child("enumber").value
                fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

                binding.etUsername.text = username.toString().toEditable()
                binding.emailEt.text = email.toString()
                binding.gender.text = gender.toString().toEditable()


                binding.designation.text = designation.toString()

                binding.userAge.text = age.toString().toEditable()

                binding.date.text = dob.toString().toEditable()

                binding.number.text = number.toString().toEditable()

                binding.enumber.text = enumber.toString().toEditable()




            }
        }
    }
}



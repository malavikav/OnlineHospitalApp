package com.poc.onlinehospitalappointment.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class ProfileInfoViewModel : ViewModel() {
    fun saveImageToFirebase(fileUri: Uri): MutableLiveData<String> {
        var imageLink: MutableLiveData<String> = MutableLiveData()
        if (fileUri != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
            refStorage.putFile(fileUri)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                            imageLink.postValue(imageUrl)
                        }
                    })
                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
        }
        return imageLink
    }


}
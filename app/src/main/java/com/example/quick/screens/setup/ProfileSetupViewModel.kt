package com.example.quick.screens.setup

import android.util.Log
import com.example.quick.models.UserDetails
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ProfileSetupViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val username = MutableStateFlow("")
    val description = MutableStateFlow("")
    val image = MutableStateFlow("")
    val link = MutableStateFlow("")

    fun updateUsername(newUsername: String) {
        username.value = newUsername
    }

    fun updateDescription(newDescription: String) {
        description.value = newDescription
    }

    fun updateImage(newImage: String) {
        image.value = newImage
    }

    fun updateLink(newLink: String) {
        link.value = newLink
    }

    fun updateUserDetails() {
        val storageRef = Firebase.storage.reference.child("images/"+image.value)
        storageRef.downloadUrl.addOnSuccessListener {
            val link = it.toString()
            Log.d("Picture Url", link)
        }.addOnFailureListener {
            Log.d("Picture Url", "Failure")
        }
        val details = UserDetails(
            accountService.currentUserId,
            username.value,
            description.value,
            link.value,
            0,
            0,
            0,
            image.value
        )
        Firebase.firestore.collection("users")
            .document(accountService.currentUserId)
            .set(details)
            .addOnSuccessListener { Log.d("UserSuccess", "User Details successfully written!") }
            .addOnFailureListener { e -> Log.w("UserFail", "Error writing document", e) }
    }


}
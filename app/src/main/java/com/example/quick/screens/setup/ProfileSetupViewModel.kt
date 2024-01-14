package com.example.quick.screens.setup

import android.net.Uri
import android.util.Log
import com.example.quick.models.UserDetails
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import javax.inject.Inject
@HiltViewModel
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
        if(image.value!= "" &&description.value!= ""&&link.value!= ""&&username.value!= ""){
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


            Log.d("UserDetails","${image.value},${description.value}")
            launchErrorCatch {
                userService.updateDetails(details)
            }
        }
        else{
            Log.d("UserDetails","Failed")
        }

    }


}
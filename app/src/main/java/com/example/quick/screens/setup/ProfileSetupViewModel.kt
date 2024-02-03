package com.example.quick.screens.setup

import android.net.Uri
import android.util.Log
import com.example.quick.models.UserDetails
import com.example.quick.navigation.NavRoutes
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
// didn t know how to reuse the same instance of the HiltViewModel
    val username = MutableStateFlow("")
    val description = MutableStateFlow("")
    val image = MutableStateFlow("")
    val link = MutableStateFlow("")
    val follower = MutableStateFlow(0L)
    val following = MutableStateFlow(0L)
    val posts = MutableStateFlow(0L)

    fun init(newFollower: Long,newFollowing: Long,newPosts: Long,newUsername: String,newDescription: String,newImage: String,newLink: String) {
        follower.value = newFollower
        following.value = newFollowing
        posts.value = newPosts
        username.value = newUsername
        description.value = newDescription
        image.value = newImage
        link.value = newLink
    }
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

    fun exitSetup(openScreen: (String) -> Unit){
        openScreen(NavRoutes.Main.route)
    }

    fun updateUserDetails(openScreen: (String) -> Unit) {
        if ( description.value != "" && link.value != "" && username.value != "") {
            val details = UserDetails(
                accountService.currentUserId,
                username.value,
                description.value,
                link.value,
                follower.value,
                following.value,
                posts.value,
                image.value
            )

            Log.d("UserDetails", "${image.value},${description.value}")
            launchErrorCatch {
                userService.updateDetails(details)
                openScreen(NavRoutes.Main.route)
            }
        } else {
            Log.d("UserDetails", "Failed")
        }

    }


}
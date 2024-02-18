package com.example.quick.screens.profile

import android.util.Log
import com.example.quick.models.Post
import com.example.quick.models.UserDetails
import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
@HiltViewModel

class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val userDetails = MutableStateFlow(default_details)
    val initialized = MutableStateFlow(false)
    val posts = MutableStateFlow(listOf(Post()))
    val isRefreshing  =  MutableStateFlow(false)

    init{
        Log.d("Profile Access","accessed")
        launchErrorCatch {
            posts.value = userService.getUserPosts(accountService.currentUserId)
            userDetails.value =
                userService.readDetails(accountService.currentUserId) ?: default_details
            delay(200)
            initialized.value = true
        }
    }

    fun onLogOutClick() {
        launchErrorCatch {
            accountService.signOut()
            initialized.value = false
        }
    }

    fun onEditProfileClick(openScreen: (String) -> Unit) {
        openScreen(NavRoutes.ProfileSetup.route)
    }

    fun refreshPosts(){
        launchErrorCatch {
            isRefreshing.value = true
            posts.value = userService.getUserPosts(accountService.currentUserId)
            userDetails.value =
                userService.readDetails(accountService.currentUserId) ?: userDetails.value
            delay(400)
            isRefreshing.value = false
        }
    }

    companion object {
        private val default_details = UserDetails(
            "Cat",
            username = "Cat",
            description = "I am a cute cat",
            link = "https://www.twitch.tv/omie",
            followers = 223,
            following = 432,
            posts = 0,
            picture = "https://firebasestorage.googleapis.com/v0/b/quick-de5b2.appspot.com/o/images%2Fomi_cat.png?alt=media&token=58c2eff1-90d3-43ff-b6ad-ddc78edf3d68"
        )
    }
}
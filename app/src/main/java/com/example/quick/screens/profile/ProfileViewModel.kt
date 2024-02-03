package com.example.quick.screens.profile

import android.util.Log
import com.example.quick.models.UserDetails
import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine
@HiltViewModel

class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val userDetails = MutableStateFlow(default_details)
    val initialized = MutableStateFlow(false)

    fun initialize() {
        Log.d("Profile Access","accessed")
        launchErrorCatch {
            userDetails.value =
                userService.readDetails(accountService.currentUserId) ?: default_details
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
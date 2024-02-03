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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val userDetails = MutableStateFlow(default_details)
    val initialized = MutableStateFlow(false)

    fun initialize() {
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
        Log.d("tag","tried")
        openScreen(NavRoutes.ProfileSetup.route)
    }

    companion object {
        private val default_details = UserDetails()
    }
}
package com.example.quick.screens.profile

import com.example.quick.models.UserDetails
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val userDetails = MutableStateFlow(default_details)

    fun initialize() {
        launchErrorCatch {
            userDetails.value = userService.readDetails(accountService.currentUserId)?:default_details
        }

    }
    fun onLogOutClick() {
        launchErrorCatch {
            accountService.signOut()
        }
    }
    companion object {
        private val default_details = UserDetails()
    }
}
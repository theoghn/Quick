package com.example.quick.screens.profile

import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : ErrorHandlingViewModel() {

    fun onLogOutClick() {
        launchErrorCatch {
            accountService.signOut()
        }
    }
}
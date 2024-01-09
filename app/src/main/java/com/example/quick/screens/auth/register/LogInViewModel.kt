package com.example.quick.screens.auth.register


import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val accountService: AccountService) : ErrorHandlingViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchErrorCatch{
            accountService.signIn(email.value, password.value)
            openAndPopUp(NavRoutes.Main.route, NavRoutes.Register.route)
        }

    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp("SIGN_UP_SCREEN", "SIGN_IN_SCREEN")
    }
}
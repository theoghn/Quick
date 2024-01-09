package com.example.quick.screens.splash


import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : ErrorHandlingViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser())
            openAndPopUp(NavRoutes.Main.route, NavRoutes.Splash.route)
        else
            openAndPopUp(NavRoutes.Register.route,  NavRoutes.Splash.route)
    }
}
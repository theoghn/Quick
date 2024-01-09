package com.example.quick


import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val accountService: AccountService,
) : ErrorHandlingViewModel() {

    fun initialize(restartApp: (String) -> Unit) {
        observeAuthenticationState(restartApp)
    }

    private fun observeAuthenticationState(restartApp: (String) -> Unit) {
        launchErrorCatch {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(NavRoutes.Splash.route)
            }
        }
    }
}

package com.example.quick

import com.example.quick.ui.theme.QuickTheme



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.auth.login.LogInScreen
import com.example.quick.screens.auth.register.RegisterScreen
import com.example.quick.screens.profile.Profile
import com.example.quick.screens.setup.ProfileSetupScreen
import com.example.quick.screens.splash.SplashScreen

@Composable
fun QuickApp() {
    QuickTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = NavRoutes.Splash.route,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    notesGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController)
    }

fun NavGraphBuilder.notesGraph(appState: AppState) {
    composable(NavRoutes.Main.route) {
        MainActivity(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(NavRoutes.Login.route) {
        LogInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(NavRoutes.Splash.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(NavRoutes.Register.route) {
        RegisterScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(NavRoutes.Profile.route) {
        Profile(openScreen = { route -> appState.navigate(route) })
    }
    composable(NavRoutes.ProfileSetup.route) {
        ProfileSetupScreen()
    }



}
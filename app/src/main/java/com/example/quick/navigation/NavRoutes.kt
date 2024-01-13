package com.example.quick.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("Home")
    object Find : NavRoutes("Find")
    object Reels : NavRoutes("Reels")
    object Profile : NavRoutes("Profile")
    object Post : NavRoutes("Post")
    object Login : NavRoutes("LoginScreen")
    object Register : NavRoutes("RegisterScreen")
    object Splash : NavRoutes("SplashScreen")

    object Main : NavRoutes("MainActivity")




}
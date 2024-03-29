package com.example.quick

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quick.navigation.NavBarItems
import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.Find
import com.example.quick.screens.home.Home
import com.example.quick.screens.post.UploadPost
import com.example.quick.screens.profile.Profile
import com.example.quick.screens.ReelsPage
import com.example.quick.screens.home.HomeViewModel
import com.example.quick.screens.profile.ProfileViewModel

@Composable
fun MainActivity(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }
    MainScreen(openScreen)

}


@Composable
fun MainScreen(openScreen: (String) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        content = { padding ->
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController,openScreen)
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }

    )

}

@Composable
fun NavigationHost(navController: NavHostController,openScreen: (String) -> Unit) {
//    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Profile.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(NavRoutes.Home.route) {

            Home()
        }
        composable(NavRoutes.Find.route) {
            Find()
        }
        composable(NavRoutes.Post.route) {
            UploadPost()
        }
        composable(NavRoutes.Reels.route) {
            ReelsPage()
        }
        composable(NavRoutes.Profile.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            Profile(viewModel,openScreen)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        var selectedIndex by rememberSaveable {
            mutableIntStateOf(4)
        }
        NavBarItems.BarItems.forEachIndexed { index, item ->
            NavigationBarItem(

//                modifier = Modifier.background(Color.Red),
                //selected is just a boolean that check if the current select one is the item defined
                selected = currentRoute == item.route,

                onClick = {
                    selectedIndex = index
                    Log.i("debug", currentRoute.toString())
                    navController.navigate(item.route) {
                        //am un stack de screenuri si se da pop pana imi gaseste screenul cautat
                        popUpTo(navController.graph.findStartDestination().id) {
                            //cred ca salveaza stack ul de dinainte
                            saveState = true
                        }
                        //se creeaza o singura copie a ecranului
                        launchSingleTop = true
                        // daca a mai fost creat ecranul o data se lanseza acela
                        restoreState = true
                    }
                },
                icon = {

                    Icon(
                        imageVector = if (selectedIndex == index) {
                            item.selected
                        } else item.unselected,
                        contentDescription = null,
                        modifier = if (selectedIndex == index) Modifier.fillMaxSize(0.55F)
                        else Modifier.fillMaxSize(0.5F)
                    )
                }

            )
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    QuickTheme(useDarkTheme = true) {
//        MainScreen()
//    }
//}
package com.example.quick.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search

import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.filled.AddCircle

import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material.icons.twotone.Search


object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            selected = Icons.Filled.Home,
            unselected = Icons.Outlined.Home,
            route = "home"
        ),
        BarItem(
            title = "Find",
            selected = Icons.Filled.Search,
            unselected = Icons.Outlined.Search,
            route = "find"
        ),
        BarItem(
            title = "Post",
            selected = Icons.Filled.AddCircle,
            unselected = Icons.Outlined.AddCircleOutline,
            route = "post"
        ),
        BarItem(
            title = "Reels",
            selected = Icons.Filled.SmartDisplay,
            unselected = Icons.Outlined.SmartDisplay,
            route = "reels"
        ),
        BarItem(
            title = "Profile",
            selected = Icons.Filled.AccountCircle,
            unselected = Icons.Outlined.AccountCircle,
            route = "profile"
        )

    )
}
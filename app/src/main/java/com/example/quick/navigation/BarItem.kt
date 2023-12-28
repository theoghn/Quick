package com.example.quick.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val title: String,
    val selected: ImageVector,
    val unselected: ImageVector,
    val route: String
)

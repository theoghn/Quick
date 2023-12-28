package com.example.quick.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.quick.BottomNavigationBar
import com.example.quick.NavigationHost

@Composable
fun Home(){
    Scaffold(
        topBar = { Text(text = "Quick", fontSize = 23.sp)  },
        content =  {padding ->
            Column(Modifier.padding(padding)) {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(
                        text = "Home",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 46.sp
                    )

                }
            }}
    )
}
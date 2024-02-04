package com.example.quick.screens.post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun Post() {


    val lightBlue = Color(0xFF6495ED)
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New Post",
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { },
                    modifier = Modifier.padding(6.dp)
                ) {
                    Text(text = "Share it", color = lightBlue, fontSize = 18.sp)
                }

            }

        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            PhotoPicker()
        }

    }
}

@Composable
fun PhotoPicker(viewModel: PostViewModel = hiltViewModel()) {
    val caption by viewModel.caption.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val uri by viewModel.image.collectAsState()

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uris ->
            viewModel.updateImage(uris.toString())
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) { focusManager.clearFocus() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(if (uri != "") Color.White else Color.Gray),
//            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = uri,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .focusable()

            )
        }

        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )

        }, modifier = Modifier.focusable()) {
            Text("Select Image")
        }

        val rainbowColors = listOf(
            Color(0xFFFF7F27), // Orange
            Color(0xFFFFED00), // Yellow
            Color(0xFF22B14C), // Green
            Color(0xFF00A2E8), // Blue
            Color(0xFF652D8B)  // Indigo
        )
        val brush = remember {
            Brush.linearGradient(
                colors = rainbowColors
            )
        }

        TextField(
            value = caption,
            onValueChange = { viewModel.updateCaption(it) },
            textStyle = TextStyle(brush = brush),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(5.dp)
                .focusRequester(focusRequester)
        )
    }
}
// top bar cu new post and next up
//maybe photo editor
// next pressed-  hide photo editor show the photo and something to add a caption
package com.example.quick.screens.setup

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quick.screens.auth.register.customTextFieldColors
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage

@Composable
fun customTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedIndicatorColor = Color.White,
    unfocusedIndicatorColor = Color.White,
)

@Composable
fun ProfileSetupScreen(
//    profileSetupViewModel: ProfileSetupViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Setup your profile", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        PhotoPicker()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )


        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f, false)
                .padding(16.dp, 4.dp)
                .clip(RoundedCornerShape(30))
                .border(
                    BorderStroke(width = 2.dp, color = Color.Gray),
                    shape = RoundedCornerShape(30)
                ),
            colors = customTextFieldColors(),
            value = "username.value",
            onValueChange = { "viewModel.updateEmail(it)" },
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f, false)
                .padding(16.dp, 4.dp)
                .clip(RoundedCornerShape(30))
                .border(
                    BorderStroke(width = 2.dp, color = Color.Gray),
                    shape = RoundedCornerShape(30)
                ),
            colors = customTextFieldColors(),
            value = "description.value",
            onValueChange = { "viewModel.updateEmail(it)" },
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f, false)
                .padding(16.dp, 4.dp)
                .clip(RoundedCornerShape(30))
                .border(
                    BorderStroke(width = 2.dp, color = Color.Gray),
                    shape = RoundedCornerShape(30)
                ),
            colors = customTextFieldColors(),
            value = "link.value",
            onValueChange = { "viewModel.updateEmail(it)" },
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
    }

}

@Composable
fun PhotoPicker() {
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp)
                .background(Color.Gray),
        ) {
            AsyncImage(
                model = uri,
                contentDescription = null,
            )
        }
        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )

        }) {
            Text("Pick Single Image")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSetup() {
    ProfileSetupScreen()
}
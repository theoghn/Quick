package com.example.quick.screens.setup

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LineStyle
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.compose.AsyncImage
import com.example.quick.models.UserDetails
import com.example.quick.screens.profile.ProfileViewModel
import kotlinx.coroutines.delay

// Don t know how to have the same instance of the ProfileViewModel
@Composable
fun ProfileSetupScreen(
    setupViewModel: ProfileSetupViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    openScreen: (String) -> Unit
) {
    val username by setupViewModel.username.collectAsState()
    val description by setupViewModel.description.collectAsState()
    val link by setupViewModel.link.collectAsState()
    val pic by setupViewModel.image.collectAsState()


    // Bad implementation of uploading the current details into the thing
    val isInit by profileViewModel.initialized.collectAsState()
    val userDetails by profileViewModel.userDetails.collectAsState()
    var initialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        profileViewModel.initialize()

        delay(500)
        setupViewModel.init(userDetails.followers,userDetails.following,userDetails.posts,userDetails.username,userDetails.description,userDetails.picture,userDetails.link)
        Log.d("OOOOOOOOOO",userDetails.picture)
        initialized = true
    }



    Scaffold(
        topBar = {
            IconButton(
                onClick = { setupViewModel.exitSetup(openScreen) },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Setup your profile", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))

            val updateImage: (String) -> Unit = { newImage ->
                setupViewModel.updateImage(newImage)
            }
            if (initialized){
                PhotoPicker(updateImage, userDetails.picture)
            }


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
                value = username,
                onValueChange = { setupViewModel.updateUsername(it) },
                placeholder = { Text("Username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PersonPin,
                        contentDescription = "Username"
                    )
                }
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
                value = description,
                onValueChange = { setupViewModel.updateDescription(it) },
                placeholder = { Text("Description") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LineStyle,
                        contentDescription = "Description"
                    )
                }
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
                value = link,
                onValueChange = { setupViewModel.updateLink(it) },
                placeholder = { Text("Website Link") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = "Website Link"
                    )
                }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))

            Button(onClick = { setupViewModel.updateUserDetails(openScreen) }) {
                Text("Save Details")
            }

        }
    }


}

@Composable
fun PhotoPicker(update: (String) -> Unit, pic: String) {
    var uri by remember {
        mutableStateOf<String>(pic)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uris ->
            uri = uris.toString()
            update(uris.toString())
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = uri,
                contentDescription = null,
                Modifier.size(100.dp)
            )
        }
        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )

        }) {
            Text("Select Image")
        }
    }
}



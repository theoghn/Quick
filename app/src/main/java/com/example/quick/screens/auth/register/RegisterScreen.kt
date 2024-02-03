package com.example.quick.screens.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AssignmentInd
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
fun RegisterScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()

    Scaffold(
        topBar = {
            IconButton(
                onClick = {viewModel.exitRegister(openAndPopUp)},
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.AssignmentInd,
                contentDescription = "Auth image",
                modifier = modifier
                    .weight(0.1f, false)
                    .fillMaxSize(0.7f)
                    .padding(16.dp, 4.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .weight(0.3f, false)
                    .padding(16.dp, 4.dp)
                    .clip(RoundedCornerShape(30))
                    .border(
                        BorderStroke(width = 2.dp, color = Color.Gray),
                        shape = RoundedCornerShape(30)
                    ),
                colors = customTextFieldColors(),
                value = email.value,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                }
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .clip(RoundedCornerShape(30))
                    .border(
                        BorderStroke(width = 2.dp, color = Color.Gray),
                        shape = RoundedCornerShape(30)
                    ),
                colors = customTextFieldColors(),
                value = password.value,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .clip(RoundedCornerShape(30))
                    .border(
                        BorderStroke(width = 2.dp, color = Color.Gray),
                        shape = RoundedCornerShape(30)
                    ),
                colors = customTextFieldColors(),
                value = confirmPassword.value,
                onValueChange = { viewModel.updateConfirmPassword(it) },
                placeholder = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirm Password"
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            Button(
                onClick = { viewModel.onSignUpClick(openAndPopUp) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    modifier = modifier.padding(0.dp, 6.dp)
                )
            }
        }
    }
}
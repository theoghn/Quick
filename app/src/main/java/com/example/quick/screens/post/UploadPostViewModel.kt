package com.example.quick.screens.post

import android.util.Log
import com.example.quick.models.Post
import com.example.quick.navigation.NavRoutes
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UploadPostViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {
    val caption  = MutableStateFlow("")
    val image  = MutableStateFlow("")

    fun updateCaption(newCaption: String) {
        caption.value = newCaption
        Log.d("Caption",newCaption)
    }

    fun updateImage(newImage: String) {
        image.value = newImage
        Log.d("Image",newImage)
    }
    fun writePost(){
        val post = Post(media = image.value, caption = caption.value, userId = accountService.currentUserId)
        if(image.value !="" && caption.value != ""){
            launchErrorCatch {
                userService.writePost(post)
            }
        }

    }



}
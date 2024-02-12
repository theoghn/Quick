package com.example.quick.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.quick.models.Post
import com.example.quick.models.UserDetails
import com.example.quick.screens.ErrorHandlingViewModel
import com.example.quick.screens.profile.ProfileViewModel
import com.example.quick.service.AccountService
import com.example.quick.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userService: UserService
) : ErrorHandlingViewModel() {

    val posts = MutableStateFlow(listOf<Post>())
    val users = MutableStateFlow(listOf<UserDetails>())
    val numberOfRequests= MutableStateFlow(0L)
    private var lastId = MutableStateFlow("")

    init {
        numberOfRequests.value += 1

        launchErrorCatch {
//            result a pair of posts list and userdetails list
            val result = userService.getPosts(lastId.value,4L)
            delay(1000)
            Log.d ("Result",result.first.count().toString())
            posts.update { it + result.first}
            users.value = result.second
        }
    }

    fun getMorePosts(){
        numberOfRequests.value += 1
        lastId.value = posts.value.last().postId
        Log.d("Got more posts",numberOfRequests.value.toString())
        launchErrorCatch {
            Log.d("Got more posts",lastId.value)
//            result a pair of posts list and userdetails list
            val result = userService.getPosts(lastId.value,4L)
            delay(1000)
            result.first.removeAt(0)
            posts.update { it + result.first}
            users.update { it + result.second}
            lastId.value = result.first.last().postId
        }
    }




}
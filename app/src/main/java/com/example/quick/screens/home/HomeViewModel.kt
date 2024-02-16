package com.example.quick.screens.home

import android.util.Log
import com.example.quick.models.Like
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
    val currentUserLikes = MutableStateFlow(listOf<Like>())

    val numberOfRequests= MutableStateFlow(0L)
    private var lastId = MutableStateFlow("")

    init {
        numberOfRequests.value += 1

        launchErrorCatch {
//            result a pair of posts list and user details list
            val result = userService.getPosts(lastId.value,4L)
            val result2 = userService.getLikes()
//            long delay to make sure the coroutines get completed, tried using jobs but i can t make it work
            delay(2000)
            Log.d ("Result",result.first.count().toString())
            posts.update { it + result.first}
            currentUserLikes.update { result2}
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

    fun likePost(userId:String,postId:String,isLiked:Boolean){
        val newLike = Like(userId+"_"+postId,postId,userId)

        if(isLiked){
            currentUserLikes.update { it+newLike }
            posts.update { currentPosts ->
                currentPosts.map { post ->
                    if (post.postId == postId) {
                        post.copy(likeCount = post.likeCount + 1) // Update likeCount
                    } else {
                        post // Keep other posts unchanged
                    }
                }
            }
            launchErrorCatch { userService.likePost(postId) }
        }
        else {
            currentUserLikes.update { it.filter { item -> item.likeId != newLike.likeId } }
            posts.update { currentPosts ->
                currentPosts.map { post ->
                    if (post.postId == postId) {
                        post.copy(likeCount = post.likeCount - 1) // Update likeCount
                    } else {
                        post // Keep other posts unchanged
                    }
                }
            }
            launchErrorCatch { userService.unlikePost(postId) }

        }

    }



}
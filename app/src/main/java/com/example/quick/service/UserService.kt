package com.example.quick.service

import com.example.quick.models.Like
import com.example.quick.models.Post
import com.example.quick.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserService {
    val currentUserDetails: Flow<UserDetails?>
    suspend fun readDetails(userId: String): UserDetails?
    suspend fun updateDetails(userDetails: UserDetails)
    suspend fun writePost(post: Post)
    suspend fun getUserPosts(userId: String): MutableList<Post>
    suspend fun getPosts(start: String, postsNumber: Long): Pair<MutableList<Post>, MutableList<UserDetails>>
    suspend fun getLikes():MutableList<Like>

    suspend fun likePost(postId:String)
    suspend fun unlikePost(postId:String)

}
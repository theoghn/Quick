package com.example.quick.models
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId

data class UserDetails(
    @DocumentId val userId: String = "Cat",
    val username : String = "Cat",
    val description: String = "I am a cute cat",
    val followers: Long = 223,
    val following: Long = 432,
    val posts:Long = 0

)


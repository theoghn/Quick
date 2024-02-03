package com.example.quick.models
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId

data class UserDetails(
    @DocumentId val userId: String = "",
    val username : String = "",
    val description: String = "",
    val link:String = "",
    val followers: Long = 0,
    val following: Long = 0,
    val posts:Long = 0,
    val picture: String = ""

)


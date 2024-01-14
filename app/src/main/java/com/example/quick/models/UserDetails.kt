package com.example.quick.models
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId

data class UserDetails(
    @DocumentId val userId: String = "Cat",
    val username : String = "Cat",
    val description: String = "I am a cute cat",
    val link:String = "https://www.twitch.tv/omie",
    val followers: Long = 223,
    val following: Long = 432,
    val posts:Long = 0,
    val picture: String = "https://firebasestorage.googleapis.com/v0/b/quick-de5b2.appspot.com/o/images%2Fomi_cat.png?alt=media&token=58c2eff1-90d3-43ff-b6ad-ddc78edf3d68"

)


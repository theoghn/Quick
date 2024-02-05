package com.example.quick.models

import com.google.firebase.firestore.DocumentId

data class Post(
    @DocumentId val postId: String = "",
    val caption: String = "",
    val likeCount: Long = 0L,
    val media: String = "",
    val userId: String = ""
)

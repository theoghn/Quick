package com.example.quick.models

import com.google.firebase.firestore.DocumentId

data class Like(
    @DocumentId val likeId :String ="",
    val postId :String="",
    val userId :String=""
)

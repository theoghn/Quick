package com.example.quick.service

import android.content.ContentValues.TAG
import android.util.Log
import com.example.quick.models.UserDetails
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.toObject

class UserServiceImpl @Inject constructor(private val auth:AccountService):UserService {
    override val currentUserDetails: Flow<UserDetails> = callbackFlow {
        Firebase.firestore
            .collection("users")
            .whereEqualTo("userID", auth.currentUserId)
            .dataObjects<UserDetails>()
    }

    override suspend fun readDetails(userId: String): UserDetails?{
        return Firebase.firestore
            .collection("users")
            .document(userId).get().await().toObject()
    }


}
package com.example.quick.service

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.quick.models.UserDetails
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.ktx.storage
import java.io.File

class UserServiceImpl @Inject constructor(private val auth: AccountService) : UserService {
    override val currentUserDetails: Flow<UserDetails> = callbackFlow {
        Firebase.firestore
            .collection("users")
            .whereEqualTo("userID", auth.currentUserId)
            .dataObjects<UserDetails>()
    }

    override suspend fun readDetails(userId: String): UserDetails? {
        return Firebase.firestore
            .collection("users")
            .document(userId).get().await().toObject()
    }


    override suspend fun updateDetails(userDetails: UserDetails) {

        if (userDetails.picture.contains("https://firebasestorage.googleapis.com/v0/b/quick-de5b2.appspot.com/o/images",true) || userDetails.picture == ""){
            val details =
                UserDetails(
                    userDetails.userId,
                    userDetails.username,
                    userDetails.description,
                    userDetails.link,
                    userDetails.followers,
                    userDetails.following,
                    userDetails.posts,
                    userDetails.picture
                )
            Firebase.firestore.collection("users")
                .document(auth.currentUserId)
                .set(details)
                .addOnSuccessListener {
                    Log.d(
                        "UserSuccess",
                        "User Details successfully written without uploading the image!"
                    )
                }
                .addOnFailureListener { e -> Log.w("UserFail", "Error writing document", e) }
            return
        }
        val file = userDetails.picture.toUri()

        val storageRef = Firebase.storage.reference.child("images/${file.lastPathSegment}")
        val uploadTask = storageRef.putFile(file)
        uploadTask.addOnFailureListener {
            Log.d(
                "UserFails",
                "User Details failed to be written!"
            )
        }.addOnSuccessListener { taskSnapshot ->
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                val imgUrl = uri.toString()
                val details =
                    UserDetails(
                        userDetails.userId,
                        userDetails.username,
                        userDetails.description,
                        userDetails.link,
                        userDetails.followers,
                        userDetails.following,
                        userDetails.posts,
                        imgUrl
                    )
                Firebase.firestore.collection("users")
                    .document(auth.currentUserId)
                    .set(details)
                    .addOnSuccessListener {
                        Log.d(
                            "UserSuccess",
                            "User Details successfully written!"
                        )
                    }
                    .addOnFailureListener { e -> Log.w("UserFail", "Error writing document", e) }

            }
        }


    }


}
package com.example.quick.service

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.quick.models.Post
import com.example.quick.models.UserDetails
import com.google.firebase.firestore.FieldValue
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

    // this is a bad implementation , I did it to get it done without using update method from firebase
    override suspend fun updateDetails(userDetails: UserDetails) {
        // if user didn t choose a new image the link will be a firebase link, so no need to upload the image
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
                "Image",
                "Image upload failed"
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

    override suspend fun writePost(post: Post) {
        // upload image
        // if image uploaded,add post
        //if post added correctly update the post count of the user
        val file = post.media.toUri()
        val storageRef = Firebase.storage.reference.child("images/${file.lastPathSegment}")
        val uploadTask = storageRef.putFile(file)
        var success = false
        var imgUploadUrl = ""
        uploadTask.addOnFailureListener {
            Log.d(
                "Image",
                "Image upload failed"
            )
        }.addOnSuccessListener { taskSnapshot ->
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                imgUploadUrl = uri.toString()
                Log.d(
                    "Image",
                    "Image uploaded at $imgUploadUrl"
                )
                success = true
                val data = hashMapOf(
                    "caption" to post.caption,
                    "likeCount" to 0L,
                    "media" to imgUploadUrl,
                    "userId" to post.userId
                )

                Firebase.firestore.collection("posts")
                    .add(data)
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                        success = false
                    }
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot written with ID: ${it.id}")
                        val postRef = Firebase.firestore.collection("users").document(auth.currentUserId)
                        postRef.update("posts", FieldValue.increment(1))
                    }
            }
        }
    }

    override suspend fun getUserPosts(userId: String): MutableList<Post> {
        val posts: MutableList<Post> = mutableListOf()
        Firebase.firestore.collection("posts")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("Got Post","post")
                    val p = document.toObject<Post>()
                    posts.add(p)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return posts
    }


}
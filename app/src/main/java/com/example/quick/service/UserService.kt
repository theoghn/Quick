package com.example.quick.service

import com.example.quick.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserService {
    val currentUserDetails: Flow<UserDetails?>
    suspend fun readDetails(userId: String): UserDetails?
}
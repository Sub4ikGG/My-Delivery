package ru.spoonbill.droid.data.core.features.user.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val token: String,
    val fullName: String? = null,
    val email: String? = null
)

@Serializable
data class UserResponse(
    val id: Long,
    val phone: String,
    val clientId: Int,
    val fullName: String? = null,
    val email: String? = null,
    val address: String? = null
)

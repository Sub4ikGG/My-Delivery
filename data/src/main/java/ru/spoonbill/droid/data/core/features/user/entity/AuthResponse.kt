package ru.spoonbill.droid.data.core.features.user.entity

data class AuthResponse(
    val userResponse: UserResponse,
    val token: String,
)
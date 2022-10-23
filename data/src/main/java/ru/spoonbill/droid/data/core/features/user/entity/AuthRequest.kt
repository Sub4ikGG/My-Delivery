package ru.spoonbill.droid.data.core.features.user.entity

data class AuthRequest(
    val phone: String,
    val queueCode: String
)
package ru.spoonbill.droid.data.core.features.user.entity

import kotlinx.serialization.Serializable

@Serializable
data class QueueRequest(
    val phone: String
)
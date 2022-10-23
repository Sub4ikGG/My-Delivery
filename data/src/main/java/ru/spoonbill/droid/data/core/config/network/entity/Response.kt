package ru.spoonbill.droid.data.core.config.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val message: String?,
    val status: String?,
)

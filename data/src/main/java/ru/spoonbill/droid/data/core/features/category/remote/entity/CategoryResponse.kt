package ru.spoonbill.droid.data.core.features.category.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: Long,
    val name: String,
    val imageUrl: String? = null,
    val parentId: Long = -1,
)
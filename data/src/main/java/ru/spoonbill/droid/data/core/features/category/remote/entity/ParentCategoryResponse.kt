package ru.spoonbill.droid.data.core.features.category.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class ParentCategoryResponse(
    val id: Long,
    val name: String,
    val childCategories: List<CategoryResponse>
)
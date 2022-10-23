package ru.spoonbill.droid.data.core.features.category.entity

data class Category(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val imageUrl: String? = null,
    val parentId: Long? = null,
)
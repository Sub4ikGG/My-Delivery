package ru.spoonbill.droid.data.core.features.category.entity

data class ParentCategory(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val childCategories: List<Category>
)
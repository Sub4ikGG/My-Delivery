package ru.spoonbill.droid.ui.catalog.model

data class ChildCategoryUi(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val imageUrl: String?,
    val parentId: Long?,
)

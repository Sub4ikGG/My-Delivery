package ru.spoonbill.droid.data.core.features.category.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithChild(
    @Embedded val category: CategoryEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "parentId"
    )
    val childCategories: List<CategoryEntity>
)

package ru.spoonbill.droid.data.core.features.category.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    val id: Long = 0,
    @PrimaryKey(autoGenerate = false) val categoryId: Long,
    val name: String,
    val imageUrl: String? = null,
    val parentId: Long = -1,
)
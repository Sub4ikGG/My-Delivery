package ru.spoonbill.droid.data.core.features.category.local.dao

import androidx.room.*
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryEntity
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryWithChild

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories WHERE parentId = -1")
    fun getAll(): List<CategoryWithChild>

    @Query("SELECT * FROM categories WHERE categoryId = :categoryId")
    fun getById(categoryId: Long): CategoryEntity
}
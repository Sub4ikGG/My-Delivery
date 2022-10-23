package ru.spoonbill.droid.data.core.features.category.remote.data_source

import ru.spoonbill.droid.data.core.features.category.remote.entity.CategoryResponse
import ru.spoonbill.droid.data.core.features.category.remote.entity.ParentCategoryResponse

internal interface CategoryDataSource {
    suspend fun fetchCategories(): List<ParentCategoryResponse>
}
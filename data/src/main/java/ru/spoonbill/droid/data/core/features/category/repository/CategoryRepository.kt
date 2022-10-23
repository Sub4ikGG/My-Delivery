package ru.spoonbill.droid.data.core.features.category.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.spoonbill.droid.data.core.entity.NetworkResult
import ru.spoonbill.droid.data.core.features.category.entity.Category
import ru.spoonbill.droid.data.core.features.category.entity.ParentCategory

interface CategoryRepository {

    suspend fun getAndCacheCategories(dispatcher: CoroutineDispatcher = Dispatchers.IO): NetworkResult

    suspend fun getCategories(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<ParentCategory>

    suspend fun getCategoryById(id: Long, dispatcher: CoroutineDispatcher = Dispatchers.IO): Category
}
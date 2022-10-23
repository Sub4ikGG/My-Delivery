package ru.spoonbill.droid.data.core.features.category.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.spoonbill.droid.data.core.entity.NetworkResult
import ru.spoonbill.droid.data.core.features.category.entity.Category
import ru.spoonbill.droid.data.core.features.category.entity.ParentCategory
import ru.spoonbill.droid.data.core.features.category.entity.toEntity
import ru.spoonbill.droid.data.core.features.category.entity.toModel
import ru.spoonbill.droid.data.core.features.category.local.dao.CategoryDao
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryEntity
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryWithChild
import ru.spoonbill.droid.data.core.features.category.remote.data_source.CategoryDataSource
import ru.spoonbill.droid.data.core.features.category.remote.entity.CategoryResponse
import ru.spoonbill.droid.data.core.features.category.remote.entity.ParentCategoryResponse

internal class CategoryRepositoryImpl(
    private val remoteDataSource: CategoryDataSource,
    private val dao: CategoryDao
) : CategoryRepository {
    override suspend fun getAndCacheCategories(
        dispatcher: CoroutineDispatcher
    ): NetworkResult = withContext(dispatcher) {
        if (dao.getAll().isEmpty()) {
            try {
                dao.insertAll(mapCategories(remoteDataSource.fetchCategories()))
                return@withContext NetworkResult.Success
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext NetworkResult.Failure(e)
            }
        }
        return@withContext NetworkResult.Success
    }

    override suspend fun getCategories(
        dispatcher: CoroutineDispatcher
    ): List<ParentCategory> = withContext(dispatcher) {
        return@withContext dao.getAll().map(CategoryWithChild::toModel)
    }

    override suspend fun getCategoryById(id: Long, dispatcher: CoroutineDispatcher): Category = withContext(dispatcher) {
        return@withContext dao.getById(id).toModel()
    }

    private fun mapCategories(response: List<ParentCategoryResponse>): List<CategoryEntity> {
        val result = mutableListOf<CategoryEntity>()
        response.forEach { parent ->
            result.add(parent.toEntity())
            parent.childCategories.forEach { child -> result.add(child.toEntity()) }
        }
        return result
    }
}
package ru.spoonbill.droid.data.core.features.category.remote.data_source

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import ru.spoonbill.droid.data.core.config.network.Paths
import ru.spoonbill.droid.data.core.config.network.client
import ru.spoonbill.droid.data.core.features.category.entity.Category
import ru.spoonbill.droid.data.core.features.category.entity.ParentCategory
import ru.spoonbill.droid.data.core.features.category.remote.entity.CategoryResponse
import ru.spoonbill.droid.data.core.features.category.remote.entity.ParentCategoryResponse

class CategoryRemoteDataSource : CategoryDataSource {
    override suspend fun fetchCategories(): List<ParentCategoryResponse> {
        return client.get(Paths.CATEGORY).body()
    }
}
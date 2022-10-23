package ru.spoonbill.droid.data.core.features.category.entity

import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryEntity
import ru.spoonbill.droid.data.core.features.category.local.entity.CategoryWithChild
import ru.spoonbill.droid.data.core.features.category.remote.entity.CategoryResponse
import ru.spoonbill.droid.data.core.features.category.remote.entity.ParentCategoryResponse

fun CategoryResponse.toEntity() = CategoryEntity(
    categoryId = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    parentId = this.parentId
)

fun ParentCategoryResponse.toEntity() = CategoryEntity(
    categoryId = this.id,
    name = this.name,
)

fun CategoryEntity.toModel() = Category(
    id = this.id,
    categoryId = this.categoryId,
    name = this.name,
    imageUrl = this.imageUrl,
    parentId = this.parentId,
)

fun CategoryWithChild.toModel() = ParentCategory(
    id = this.category.id,
    categoryId = this.category.categoryId,
    name = this.category.name,
    childCategories = this.childCategories.map(CategoryEntity::toModel)
)
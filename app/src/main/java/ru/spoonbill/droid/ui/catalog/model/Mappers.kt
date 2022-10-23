package ru.spoonbill.droid.ui.catalog.model

import ru.spoonbill.droid.data.core.features.category.entity.Category
import ru.spoonbill.droid.data.core.features.category.entity.ParentCategory

fun ParentCategory.toUi() = ParentCategoryUi(
    id = this.id,
    name = this.name,
    childCategories = this.childCategories.map(Category::toUi)
)

fun Category.toUi() = ChildCategoryUi(
    id = this.id,
    name = this.name,
    categoryId = this.categoryId,
    imageUrl = this.imageUrl,
    parentId = this.parentId,
)
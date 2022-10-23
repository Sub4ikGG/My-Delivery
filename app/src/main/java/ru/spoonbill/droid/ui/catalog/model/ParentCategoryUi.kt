package ru.spoonbill.droid.ui.catalog.model

import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi

data class ParentCategoryUi(
    val id: Long,
    val name: String,
    val childCategories: List<ChildCategoryUi>
)

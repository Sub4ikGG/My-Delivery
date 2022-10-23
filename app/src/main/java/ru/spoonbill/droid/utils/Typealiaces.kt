package ru.spoonbill.app.utils

import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi
import ru.spoonbill.droid.ui.home.entity.ProductUi

typealias OnProductItemClick = (ProductUi) -> Unit
typealias OnCategoryItemClick = (ChildCategoryUi) -> Unit

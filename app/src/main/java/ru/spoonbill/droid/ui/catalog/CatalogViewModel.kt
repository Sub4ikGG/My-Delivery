package ru.spoonbill.droid.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.category.entity.ParentCategory
import ru.spoonbill.droid.data.core.features.category.repository.CategoryRepository
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.ui.catalog.model.ParentCategoryUi
import ru.spoonbill.droid.ui.catalog.model.toUi
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.home.entity.toModel
import ru.spoonbill.droid.ui.home.entity.toUi

class CatalogViewModel(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize() = viewModelScope.launch {
        launch { fetchRecommendedProducts() }
        launch { fetchCategories() }
    }

    private suspend fun fetchCategories() {
        _uiStateFlow.update { value -> value.copy(parentCategories = categoryRepository.getCategories().map(ParentCategory::toUi)) }
    }

    private suspend fun fetchRecommendedProducts() {
        productRepository.getRecommendedProductsFlow().collectLatest { recommendedProducts ->
            _uiStateFlow.update { value -> value.copy(recommendedProducts = recommendedProducts.map(Product::toUi)) }
        }
    }

    fun changeFavoriteStatus(product: ProductUi, isFavorite: Boolean) {
        viewModelScope.launch {
            val model = product.copy(isFavorite = isFavorite).toModel()
            productRepository.updateProduct(model)
        }
    }

    data class UiState(
        val parentCategories: List<ParentCategoryUi> = listOf(),
        val recommendedProducts: List<ProductUi> = listOf(),
    )
}
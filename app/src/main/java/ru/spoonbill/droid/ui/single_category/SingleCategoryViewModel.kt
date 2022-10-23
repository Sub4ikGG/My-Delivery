package ru.spoonbill.droid.ui.single_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.category.repository.CategoryRepository
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi
import ru.spoonbill.droid.ui.catalog.model.toUi
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.home.entity.toModel
import ru.spoonbill.droid.ui.home.entity.toUi

class SingleCategoryViewModel(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize(categoryId: Long) = viewModelScope.launch {
        launch { fetchCategory(categoryId) }
        launch { fetchProductByCategoryId(categoryId) }
    }

    private suspend fun fetchCategory(categoryId: Long) {
        val category = categoryRepository.getCategoryById(categoryId).toUi()
        _uiStateFlow.update { value -> value.copy(category = category) }
    }

    private suspend fun fetchProductByCategoryId(categoryId: Long) {
        productRepository.getProductsByCategoryId(categoryId).collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(products = products.map(Product::toUi)) }
        }
    }

    fun changeQuantityInCart(product: ProductUi, quantityInCart: Int) {
        viewModelScope.launch {
            val model = product.copy(quantityInCart = quantityInCart).toModel()
            productRepository.updateProduct(model)
        }
    }

    fun changeFavoriteStatus(product: ProductUi, isFavorite: Boolean) {
        viewModelScope.launch {
            productRepository.updateProduct(product.copy(isFavorite = isFavorite).toModel())
        }
    }

    data class UiState(
        val products: List<ProductUi> = listOf(),
        val category: ChildCategoryUi? = null
    )
}
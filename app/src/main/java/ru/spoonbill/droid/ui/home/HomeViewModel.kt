package ru.spoonbill.droid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.entity.Promotion
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.data.core.features.story.model.Story
import ru.spoonbill.droid.data.core.features.story.repository.StoryRepository
import ru.spoonbill.droid.ui.home.entity.*

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val storyRepository: StoryRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize() {
        fetchData()
    }

    fun changeFavoriteStatus(product: ProductUi, isFavorite: Boolean) {
        viewModelScope.launch {
            val model = product.copy(isFavorite = isFavorite).toModel()
            productRepository.updateProduct(model)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                launch { fetchRecommendedProducts() }
                launch { fetchPopularProducts() }
                launch { fetchActualProducts() }
                launch { fetchPromotions() }
                launch { fetchStories() }
            } catch (e: Exception) {
                _uiStateFlow.update { value -> value.copy(errorMessages = listOf(e.message)) }
            }
        }
    }

    private suspend fun fetchRecommendedProducts() {
        productRepository.getRecommendedProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(recommendedProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchPopularProducts() {
        productRepository.getPopularProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(popularProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchActualProducts() {
        productRepository.getActualProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(actualProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchPromotions(dispatcher: CoroutineDispatcher = Dispatchers.IO) = withContext(dispatcher) {
        _uiStateFlow.update { value -> value.copy(promotions = productRepository.getPromotions().map(Promotion::toUi))  }
    }

    private suspend fun fetchStories(dispatcher: CoroutineDispatcher = Dispatchers.IO) = withContext(dispatcher) {
        _uiStateFlow.update { value -> value.copy(stories = storyRepository.fetchStories().map(Story::toUi))  }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val recommendedProducts: List<ProductUi> = listOf(),
        val popularProducts: List<ProductUi> = listOf(),
        val actualProducts: List<ProductUi> = listOf(),
        val stories: List<StoryUi> = listOf(),
        val promotions: List<PromotionUi> = listOf(),
        val errorMessages: List<String?>? = null,
    )
}
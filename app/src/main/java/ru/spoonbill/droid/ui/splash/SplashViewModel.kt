package ru.spoonbill.droid.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.entity.NetworkResult
import ru.spoonbill.droid.data.core.features.cart.model.Cart
import ru.spoonbill.droid.data.core.features.cart.repository.CartRepository
import ru.spoonbill.droid.data.core.features.category.repository.CategoryRepository
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.data.core.features.user.manager.UserManager
import ru.spoonbill.droid.ui.splash.SplashViewModel.UiEvent.*

class SplashViewModel(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _uiEventsFlow = MutableStateFlow<UiEvent?>(null)
    val uiEventsFlow = _uiEventsFlow.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            delay(2000)
            checkIfSignedIn()
            fetchData()
        }
    }

    fun createCart() {
        viewModelScope.launch {
            _uiEventsFlow.value = try {
                CartCreationSuccessEvent
            } catch (e: Exception) {
                CartCreationFailedEvent(e)
            }
        }
    }

    fun checkIfSignedIn() {
        _uiEventsFlow.value = if (userManager.exists()) SignedInEvent else NotSignedInEvent
    }

    fun checkIfCartExists() {
        viewModelScope.launch {
            //_uiEventsFlow.value = if (cartRepository.cartExists()) CartExistsEvent else CartNotExistsEvent
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            _uiEventsFlow.value = try {
                val productResult = async { productRepository.getAndCacheAllProducts() }
                val categoryResult = async { categoryRepository.getAndCacheCategories() }
                val isProductFetchingSuccess = productResult.await() == NetworkResult.Success
                val isCategoryFetchingSuccess = categoryResult.await() == NetworkResult.Success
                if(isProductFetchingSuccess && isCategoryFetchingSuccess) {
                    FetchingSuccessEvent
                } else {
                    FetchingFailureEvent
                }
            } catch (e: Exception) {
                FetchingFailureEvent
            }
        }
    }

    sealed class UiEvent {
        object FetchingSuccessEvent : UiEvent()
        object FetchingFailureEvent : UiEvent()
        object CartExistsEvent : UiEvent()
        object CartNotExistsEvent : UiEvent()
        object SignedInEvent : UiEvent()
        object NotSignedInEvent : UiEvent()
        object CartCreationSuccessEvent : UiEvent()
        data class CartCreationFailedEvent(val e: Exception) : UiEvent()
    }
}
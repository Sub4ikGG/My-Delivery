package ru.spoonbill.droid.domain.features.cart.model

sealed class CartEvent {
    object Success : CartEvent()
    data class Failure(val e: Exception) : CartEvent()
}

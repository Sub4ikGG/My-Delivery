package ru.spoonbill.droid.domain.features.cart.model

data class CartProduct(
    val id: Long,
    val cartId: Long,
    val productId: Long,
    val productName: String,
    val productPrice: Float,
    val productAmount: Int,
    val productQuantity: Int,
    val productImageUrl: String?,
)

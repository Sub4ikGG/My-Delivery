package ru.spoonbill.droid.ui.cart.model

import ru.spoonbill.droid.ui.cart.model.CartProductUi

data class CartUi(
    val id: Long,
    val deliveryAddress: String?,
    val products: List<CartProductUi>
)

package ru.spoonbill.droid.domain.features.cart.usecase

import ru.spoonbill.droid.domain.features.cart.model.Cart
import ru.spoonbill.droid.domain.features.cart.model.CartEvent
import ru.spoonbill.droid.domain.features.cart.repository.CartRepository
import java.lang.Exception

class LoadCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(): CartEvent {
        return try {
            if(!cartRepository.cartExists())
                cartRepository.createCart(Cart())
            CartEvent.Success
        } catch (e: Exception) {
            CartEvent.Failure(e)
        }
    }
}
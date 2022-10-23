package ru.spoonbill.droid.domain.di

import org.koin.dsl.module
import ru.spoonbill.droid.domain.features.cart.di.cartUseCaseModule

val useCaseModule = module {
    includes(
        listOf(
            cartUseCaseModule
        )
    )
}
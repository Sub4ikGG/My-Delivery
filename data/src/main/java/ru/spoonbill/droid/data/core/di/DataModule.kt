package ru.spoonbill.droid.data.core.di

import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.di.configModule
import ru.spoonbill.droid.data.core.features.cart.di.cartModule
import ru.spoonbill.droid.data.core.features.category.di.categoryModule
import ru.spoonbill.droid.data.core.features.product.di.productModule
import ru.spoonbill.droid.data.core.features.story.di.storyModule
import ru.spoonbill.droid.data.core.features.user.di.userModule

val dataModule = module {
    includes(
        listOf(
            cartModule,
            configModule,
            categoryModule,
            productModule,
            storyModule,
            userModule
        )
    )
}
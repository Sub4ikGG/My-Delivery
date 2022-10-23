package ru.spoonbill.droid.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.spoonbill.droid.ui.cart.CartViewModel
import ru.spoonbill.droid.ui.catalog.CatalogViewModel
import ru.spoonbill.droid.ui.favorites.FavoritesViewModel
import ru.spoonbill.droid.ui.home.HomeViewModel
import ru.spoonbill.droid.ui.sign_in.SignInViewModel
import ru.spoonbill.droid.ui.single_category.SingleCategoryViewModel
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductViewModel
import ru.spoonbill.droid.ui.splash.SplashViewModel

val viewModelModule = module {
    viewModel { SplashViewModel(userManager = get(), productRepository = get(), categoryRepository = get()) }
    viewModel { SignInViewModel(userRepository = get()) }

    viewModel { HomeViewModel(productRepository = get(), storyRepository = get()) }
    viewModel { CatalogViewModel(productRepository = get(), categoryRepository = get()) }
    viewModel { FavoritesViewModel(productRepository = get()) }
    viewModel { CartViewModel(productRepository = get()) }

    viewModel { SingleProductSharedViewModel() }
    viewModel { SingleProductViewModel(productRepository = get()) }

    viewModel { SingleCategoryViewModel(categoryRepository = get(), productRepository = get()) }
}
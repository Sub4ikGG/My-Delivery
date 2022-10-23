package ru.spoonbill.droid.data.core.features.user.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.features.user.manager.UserManager
import ru.spoonbill.droid.data.core.features.user.manager.UserManagerImpl
import ru.spoonbill.droid.data.core.features.user.repository.UserRepository
import ru.spoonbill.droid.data.core.features.user.repository.UserRepositoryImpl

internal val userModule = module {
    single { UserManagerImpl(context = get()) } bind UserManager::class
    factory { UserRepositoryImpl() } bind UserRepository::class
}
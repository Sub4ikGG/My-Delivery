package ru.spoonbill.droid.data.core.features.category.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase
import ru.spoonbill.droid.data.core.features.category.remote.data_source.CategoryDataSource
import ru.spoonbill.droid.data.core.features.category.remote.data_source.CategoryRemoteDataSource
import ru.spoonbill.droid.data.core.features.category.repository.CategoryRepository
import ru.spoonbill.droid.data.core.features.category.repository.CategoryRepositoryImpl

internal val categoryModule = module {
    factory { provideCategoryDao(database = get()) }
    factory { CategoryRemoteDataSource() } bind CategoryDataSource::class
    factory { CategoryRepositoryImpl(remoteDataSource = get(), dao = get()) } bind CategoryRepository::class
}

private fun provideCategoryDao(database: SpoonbillDatabase) = database.categoryDao()
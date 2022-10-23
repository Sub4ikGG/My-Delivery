package ru.spoonbill.droid.data.core.features.story.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.features.story.data_source.StoryDataSource
import ru.spoonbill.droid.data.core.features.story.data_source.StoryRemoteDataSource
import ru.spoonbill.droid.data.core.features.story.repository.StoryRepository
import ru.spoonbill.droid.data.core.features.story.repository.StoryRepositoryImpl

internal val storyModule = module {
    single { StoryRemoteDataSource() } bind StoryDataSource::class
    factory { StoryRepositoryImpl(mDataSource = get()) } bind StoryRepository::class
}
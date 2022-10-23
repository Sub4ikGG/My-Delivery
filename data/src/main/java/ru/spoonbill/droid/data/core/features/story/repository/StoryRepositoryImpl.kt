package ru.spoonbill.droid.data.core.features.story.repository

import ru.spoonbill.droid.data.core.features.story.model.Story
import ru.spoonbill.droid.data.core.features.story.data_source.StoryDataSource

class StoryRepositoryImpl(
    private val mDataSource: StoryDataSource
) : StoryRepository {
    override suspend fun fetchStories(): List<Story> {
        return mDataSource.fetchStories()
    }
}
package ru.spoonbill.droid.data.core.features.story.data_source

import ru.spoonbill.droid.data.core.features.story.model.Story

interface StoryDataSource {

    suspend fun fetchStories(): List<Story>
}
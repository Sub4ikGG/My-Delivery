package ru.spoonbill.droid.data.core.features.story.repository

import ru.spoonbill.droid.data.core.features.story.model.Story

interface StoryRepository {

    suspend fun fetchStories(): List<Story>
}
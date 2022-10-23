package ru.spoonbill.droid.data.core.features.story.data_source

import kotlinx.coroutines.delay
import ru.spoonbill.droid.data.core.features.story.model.Story

private val stories = listOf(
    Story(1, "История покупок", "https://www.guidingtech.com/wp-content/uploads/HD-Mouth-Watering-Food-Wallpapers-for-Desktop-12_4d470f76dc99e18ad75087b1b8410ea9.jpg"),
    Story(2, "Скидки", "https://i.pinimg.com/originals/f0/b6/15/f0b615f78dd809d68ec389f4bc8d94bb.jpg"),
    Story(3, "Местные продукты", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"),
)

class StoryRemoteDataSource : StoryDataSource {
    override suspend fun fetchStories(): List<Story> {
        delay(3000)
        return stories
    }
}
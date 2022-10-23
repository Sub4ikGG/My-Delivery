package ru.spoonbill.droid.ui.home.entity

data class HomeResult(
    val lists: List<ProductCollection>,
    val promotions: List<PromotionUi>,
    val stories: List<StoryUi>
)
package ru.spoonbill.droid.data.core.features.product.model

data class Product(
    val id: Long,
    val name: String,
    val price: Float,
    val imageUri: String,
    val isFavorite: Boolean
)

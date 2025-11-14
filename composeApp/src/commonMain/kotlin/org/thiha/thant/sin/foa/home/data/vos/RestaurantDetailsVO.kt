package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDetailsVO(
    val id: Long,
    val name: String,

    @SerialName("image_url")
    val imageUrl: String,

    @SerialName("average_rating")
    val averageRating: Double,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("food_categories")
    val foodCategories: List<FoodCategoryVO>
)

@Serializable
data class FoodCategoryVO(
    val id: Long,
    val name: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("food_items")
    val foodItems: List<FoodItemVO>
)

@Serializable
data class FoodItemVO(
    val id: Long,
    val name: String,

    @SerialName("image_url")
    val imageUrl: String,

    val description: String,
    val price: Double,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)

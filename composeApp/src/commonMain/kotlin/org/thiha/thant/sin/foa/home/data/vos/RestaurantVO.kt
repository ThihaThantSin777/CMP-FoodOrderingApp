package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantVO(
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
    @SerialName("restaurant_categories")
    val restaurantCategories: List<RestaurantCategoryVO>
)

@Serializable
data class RestaurantCategoryVO(
    val id: Long,
    val name: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)


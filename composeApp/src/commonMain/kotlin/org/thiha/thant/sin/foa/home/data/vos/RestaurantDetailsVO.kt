package org.thiha.thant.sin.foa.home.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
@Entity(tableName = "food_item")
data class FoodItemVO(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo("name")
    val name: String,

    @SerialName("image_url")
    @ColumnInfo("image_url")
    val imageUrl: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("price")
    val price: Double,

    @SerialName("quantity")
    @ColumnInfo("quantity")
    val quantity: Int = 1,

    @SerialName("created_at")
    @ColumnInfo("created_at")
    val createdAt: String,

    @ColumnInfo("updated_at")
    @SerialName("updated_at")
    val updatedAt: String
)


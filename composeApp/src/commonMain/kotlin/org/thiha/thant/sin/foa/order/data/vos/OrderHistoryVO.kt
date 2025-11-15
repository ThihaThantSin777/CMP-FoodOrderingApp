package org.thiha.thant.sin.foa.order.data.vos

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderHistoryVO(
    val id: Long,
    @SerialName("order_number")
    val orderNumber: String,
    @SerialName("total_cost")
    val totalCost: Double,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("delivery_address")
    val deliveryAddress: DeliveryAddressVo,
    @SerialName("payment_method")
    val paymentMethod: PaymentMethodVo,
    @SerialName("food_items")
    val foodItems: List<FoodItemVo>
) {
    @OptIn(FormatStringsInDatetimeFormats::class)
    fun formateDate(): String {
        return LocalDateTime.parse(input = createdAt.split("Z").first()).format(
            LocalDateTime.Format {
                dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
                char(' ')
                monthName(MonthNames.ENGLISH_ABBREVIATED)

                day()
            }
        )

    }

    fun itemCount(): String {
        val count: Int = foodItems.count();
        if (count > 0) {
            return "$count items"
        }
        return "$count item"
    }
}

@Serializable
data class DeliveryAddressVo(
    val id: Long,
    @SerialName("street_address")
    val streetAddress: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class PaymentMethodVo(
    val id: Long,
    @SerialName("card_number")
    val cardNumber: String,
    @SerialName("expiry_date")
    val expiryDate: String,
    val cvv: Int,
    @SerialName("name_on_card")
    val nameOnCard: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class FoodItemVo(
    val id: Long,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    val description: String,
    val price: Double,
    val quantity: Int
)


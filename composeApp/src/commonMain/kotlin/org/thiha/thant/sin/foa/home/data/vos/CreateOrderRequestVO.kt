package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequestVo(
    @SerialName("payment_method_id")
    val paymentMethodId: Long,

    @SerialName("delivery_address_id")
    val deliveryAddressId: Long,

    @SerialName("food_items")
    val foodItems: List<FoodItemRequestVo>
)

@Serializable
data class FoodItemRequestVo(
    val id: Long,
    val quantity: Int
)


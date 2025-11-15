package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequestVO(
    @SerialName("payment_method_id")
    val paymentMethodId: Long,

    @SerialName("delivery_address_id")
    val deliveryAddressId: Long,

    @SerialName("food_items")
    val foodItems: List<FoodItemRequestVO>
)

@Serializable
data class FoodItemRequestVO(
    val id: Long,
    val quantity: Int
)


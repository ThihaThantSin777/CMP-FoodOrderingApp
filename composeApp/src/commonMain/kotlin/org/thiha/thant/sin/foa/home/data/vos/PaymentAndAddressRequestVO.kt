package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentAndAddressRequestVO(
    @SerialName("delivery_address")
    val deliveryAddress: DeliveryAddressRequestVo,

    @SerialName("payment_method")
    val paymentMethod: PaymentMethodRequestVo
)

@Serializable
data class DeliveryAddressRequestVo(
    @SerialName("street_address")
    val streetAddress: String
)

@Serializable
data class PaymentMethodRequestVo(
    @SerialName("card_number")
    val cardNumber: String,

    @SerialName("expiry_date")
    val expiryDate: String,

    val cvv: Int,

    @SerialName("name_on_card")
    val nameOnCard: String
)

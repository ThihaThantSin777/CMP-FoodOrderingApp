package org.thiha.thant.sin.foa.home.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentAndAddressVO(
    @SerialName("payment_methods")
    val paymentMethods: List<PaymentMethodVO>,

    @SerialName("delivery_addresses")
    val deliveryAddresses: List<DeliveryAddressVO>
)

@Serializable
data class PaymentMethodVO(
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
data class DeliveryAddressVO(
    val id: Long,

    @SerialName("street_address")
    val streetAddress: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)


package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO

data class ReviewOrderState(
    val paymentMethodVO: PaymentMethodVO? = null,
    val deliveryAddressVO: DeliveryAddressVO? = null,
    val orderItems: List<FoodItemVO> = listOf(),
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
)
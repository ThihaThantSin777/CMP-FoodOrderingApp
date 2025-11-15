package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO


data class CartState(
    val foodItemVO: List<FoodItemVO> = emptyList(),
    val paymentAndAddress: PaymentAndAddressVO? = null,
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
)
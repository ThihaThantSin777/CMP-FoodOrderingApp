package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO

data class CheckOutState(
    val paymentAndAddress: PaymentAndAddressVO? = null,
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
)
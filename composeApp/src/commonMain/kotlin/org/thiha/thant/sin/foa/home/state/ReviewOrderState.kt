package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO

data class ReviewOrderState(
    val deliveryAddressAndPayment: PaymentAndAddressVO?,
    val isLoading: Boolean = false,
    val isError: String = "",
)
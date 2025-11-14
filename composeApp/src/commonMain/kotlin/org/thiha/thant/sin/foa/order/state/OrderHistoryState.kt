package org.thiha.thant.sin.foa.order.state

import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO

data class OrderHistoryState(
    val orderHistory: List<OrderHistoryVO>? = listOf(),
    val isLoading: Boolean = false,
    val isError: String = "",
)
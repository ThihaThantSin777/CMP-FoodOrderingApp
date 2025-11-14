package org.thiha.thant.sin.foa.order.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO

data class OrderHistoryState(
    val orderHistory: List<OrderHistoryVO> = listOf(),
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
)
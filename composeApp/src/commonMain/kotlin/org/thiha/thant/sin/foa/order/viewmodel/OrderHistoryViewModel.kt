package org.thiha.thant.sin.foa.order.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.order.data.OrderRepository
import org.thiha.thant.sin.foa.order.state.OrderHistoryState

class OrderHistoryViewModel : ViewModel() {
    val orderRepository: OrderRepository = OrderRepository;

    private val _state = MutableStateFlow(OrderHistoryState())
    val state = _state.asStateFlow()


    fun loadOrderHistory() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                val history = orderRepository.getOrderHistory()
                _state.update {
                    it.copy(uiState = UiState.SUCCESS, orderHistory = history)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(uiState = UiState.FAIL, errorMessage = e.message ?: "")
                }
            }
        }
    }

    init {
        loadOrderHistory()
    }
}
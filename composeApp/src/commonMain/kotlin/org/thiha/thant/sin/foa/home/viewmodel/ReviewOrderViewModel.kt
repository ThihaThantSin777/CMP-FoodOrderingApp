package org.thiha.thant.sin.foa.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.HomeRepository
import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVO
import org.thiha.thant.sin.foa.home.data.vos.FoodItemRequestVO
import org.thiha.thant.sin.foa.home.state.ReviewOrderState

class ReviewOrderViewModel : ViewModel() {
    val homeRepository: HomeRepository = HomeRepository

    private val _state = MutableStateFlow(ReviewOrderState())
    val state = _state.asStateFlow()

    init {
        loadInitialData()
        observeFoodItems()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                val paymentMethod = homeRepository.getPaymentMethodInDatabase().firstOrNull()
                val deliveryMethod = homeRepository.getDeliveryAddressInDatabase().firstOrNull()

                _state.update {
                    it.copy(
                        deliveryAddressVO = deliveryMethod,
                        paymentMethodVO = paymentMethod,
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(uiState = UiState.FAIL, errorMessage = e.message ?: "")
                }
            }
        }
    }

    private fun observeFoodItems() {
        viewModelScope.launch {
            homeRepository.getFoodItemInDatabase()
                .catch { e ->
                    _state.update {
                        it.copy(
                            uiState = UiState.FAIL,
                            errorMessage = e.message ?: ""
                        )
                    }
                }
                .collect { cartItems ->
                    _state.update {
                        it.copy(
                            orderItems = cartItems,
                            uiState = UiState.SUCCESS
                        )
                    }
                }
        }
    }

    fun onTapConfirmOrder() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                val paymentMethod = _state.value.paymentMethodVO
                val deliveryMethod = _state.value.deliveryAddressVO
                val cartItems = _state.value.orderItems

                val createOrderRequestVo = CreateOrderRequestVO(
                    deliveryAddressId = deliveryMethod?.id ?: 0,
                    paymentMethodId = paymentMethod?.id ?: 0,
                    foodItems = cartItems.map {
                        FoodItemRequestVO(it.id, it.quantity)
                    }
                )

                homeRepository.submitOrder(createOrderRequestVo)

                _state.update {
                    it.copy(uiState = UiState.SUCCESS)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        uiState = UiState.FAIL,
                        errorMessage = e.message ?: ""
                    )
                }
            }
        }
    }
}
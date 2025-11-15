package org.thiha.thant.sin.foa.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.HomeRepository
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO
import org.thiha.thant.sin.foa.home.state.CartState

class CartViewModel : ViewModel() {
    private val homeRepository: HomeRepository = HomeRepository

    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(uiState = UiState.LOADING) }
            try {
                val foodItemListFromDatabase = homeRepository.getFoodItemInDatabase()
                _state.update {
                    it.copy(
                        foodItemVO = foodItemListFromDatabase,
                        uiState = UiState.SUCCESS
                    )
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

    fun onTapAddPaymentMethodAndDeliveryAddress(paymentMethodVO: PaymentMethodVO,deliveryAddressVO: DeliveryAddressVO) {
        viewModelScope.launch {
            try {
                homeRepository.addPaymentMethodInDatabase(paymentMethodVO);
                homeRepository.addDeliveryAddressInDatabase(deliveryAddressVO);
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

    fun onUpdateFoodItem(foodItemVO: FoodItemVO) {
        viewModelScope.launch {
            try {

                if (foodItemVO.quantity == 0) {
                    homeRepository.deleteFoodItemByIDInDatabase(foodItemVO.id);
                } else {
                    homeRepository.updateFoodItemInDatabase(foodItemVO)
                }

                _state.update { current ->
                    current.copy(
                        foodItemVO = current.foodItemVO.map { item ->
                            if (item.id == foodItemVO.id) foodItemVO else item
                        }
                    )
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

    fun onTapPlaceHolder() {
        viewModelScope.launch {
            _state.update { it.copy(uiState = UiState.LOADING) }
            try {
                val deliveryAddressAndPaymentMethod =
                    homeRepository.getDeliveryAddressAndPaymentMethod()
                _state.update {
                    it.copy(
                        paymentAndAddress = deliveryAddressAndPaymentMethod,
                        uiState = UiState.SUCCESS
                    )
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
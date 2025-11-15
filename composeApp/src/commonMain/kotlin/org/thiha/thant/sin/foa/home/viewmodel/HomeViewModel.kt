package org.thiha.thant.sin.foa.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.core.NOT_AUTHORIZED_KEY
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.HomeRepository
import org.thiha.thant.sin.foa.home.state.HomeState

class HomeViewModel : ViewModel() {
    val homeRepository: HomeRepository = HomeRepository;
    val authRepository: AuthRepository = AuthRepository;

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun loadRestaurants() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                val restaurants = homeRepository.getAllRestaurant();
                _state.update {
                    it.copy(restaurantList = restaurants, uiState = UiState.SUCCESS)
                }
            } catch (e: Exception) {
                val errorMessage = e.message;
                val loggedInToken = authRepository.getLoggedInUserToken()
                val isTokenExpire = (errorMessage?.contains(NOT_AUTHORIZED_KEY)
                    ?: false) && loggedInToken.isNotEmpty();

                _state.update {
                    it.copy(
                        uiState = UiState.FAIL,
                        errorMessage = e.message ?: "",
                        isTokenExpire = isTokenExpire,
                    )
                }
            }
        }
    }

    init {
        loadRestaurants()
    }

    fun onTapLogout() {
        viewModelScope.launch {
            authRepository.clearInformationFromDatabase();
        }
    }
}
package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO

data class HomeState(
    val restaurantList: List<RestaurantVO> = listOf(),
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
    val isTokenExpire: Boolean = false,
)
package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.RestaurantDetailsVO

data class RestaurantDetailsState(
    val restaurantDetails: RestaurantDetailsVO?=null,
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
)
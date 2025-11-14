package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO

data class RestaurantDetailsState(
    val restaurantDetails: RestaurantVO?,
    val isLoading: Boolean = false,
    val isError: String = "",
)
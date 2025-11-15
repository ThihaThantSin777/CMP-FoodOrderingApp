package org.thiha.thant.sin.foa.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodOrderError(

    @SerialName("error")
    val error: String
);
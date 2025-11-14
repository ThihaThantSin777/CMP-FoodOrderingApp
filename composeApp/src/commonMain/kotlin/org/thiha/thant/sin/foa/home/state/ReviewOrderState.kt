package org.thiha.thant.sin.foa.home.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState

data class ReviewOrderState(
    val uiState: UiState= UiState.NONE,
    val errorMessage: String = "",
)
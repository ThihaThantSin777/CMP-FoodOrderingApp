package org.thiha.thant.sin.foa.app.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState

data class AppState(
    val isLoggedIn: Boolean = false,
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = ""
)
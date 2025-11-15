package org.thiha.thant.sin.foa.auth.state

import org.thiha.thant.sin.foa.core.utils.enums.UiState

data class AuthState(
    val uiState: UiState = UiState.NONE,
    val errorMessage: String = "",
    val resetPasswordToken: String = "",
    val email: String="",
)
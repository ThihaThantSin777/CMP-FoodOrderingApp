package org.thiha.thant.sin.foa.profile.state

import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.core.utils.enums.UiState

data class ProfileState(
    val auth: AuthVO? = null,
    val uiState: UiState = UiState.NONE,
    val isErrorFromOnTapLogout: Boolean = false,
    val isErrorFromProfileFetch: Boolean = false,
    val errorMessage: String = ""
)
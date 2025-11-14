package org.thiha.thant.sin.foa.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.state.AuthState
import org.thiha.thant.sin.foa.core.utils.enums.UiState

class LoginViewModel : ViewModel() {
    val authRepository: AuthRepository = AuthRepository;

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onTapLogin(email: String, password: String) {
        _state.update {
            it.copy(uiState = UiState.LOADING)
        }
        viewModelScope.launch {
            try {
                authRepository.login(LoginRequestVO(email = email, password = password))
                _state.update {
                    it.copy(uiState = UiState.SUCCESS)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(uiState = UiState.FAIL, errorMessage = e.message)
                }
            }
        }
    }
}
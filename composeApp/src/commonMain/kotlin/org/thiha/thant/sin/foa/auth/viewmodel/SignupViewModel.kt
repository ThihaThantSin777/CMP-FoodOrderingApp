package org.thiha.thant.sin.foa.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO
import org.thiha.thant.sin.foa.auth.state.AuthState
import org.thiha.thant.sin.foa.core.utils.enums.UiState

class SignupViewModel : ViewModel() {
    val authRepository: AuthRepository = AuthRepository;

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onTapRegister(email: String, password: String, fullName: String) {
        _state.update {
            it.copy(uiState = UiState.LOADING)
        }
        viewModelScope.launch {
            try {
                authRepository.register(
                    RegisterRequestVO(
                        email = email,
                        password = password,
                        fullName = fullName
                    )
                )
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
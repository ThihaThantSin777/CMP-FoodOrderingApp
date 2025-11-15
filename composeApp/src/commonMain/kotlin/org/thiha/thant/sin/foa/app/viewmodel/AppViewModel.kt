package org.thiha.thant.sin.foa.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.app.state.AppState
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.core.utils.enums.UiState

class AppViewModel : ViewModel() {
    val authRepository: AuthRepository = AuthRepository;

    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                val isLoggedIn = authRepository.isUserLoggedIn();
                _state.update {
                    it.copy(uiState = UiState.SUCCESS, isLoggedIn = isLoggedIn)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(uiState = UiState.FAIL, errorMessage = e.message ?: "")
                }
            }
        }
    }

    fun onTapUIStateNone() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.NONE)
            }
        }
    }
}
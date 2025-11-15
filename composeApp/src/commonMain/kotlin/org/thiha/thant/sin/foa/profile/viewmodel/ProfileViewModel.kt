package org.thiha.thant.sin.foa.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.profile.state.ProfileState

class ProfileViewModel : ViewModel() {
    val authRepository: AuthRepository = AuthRepository;

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadProfileData()
    }

    fun loadProfileData() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                val user = authRepository.getLoggedInUser();
                _state.update {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        auth = user,
                        isErrorFromProfileFetch = false,
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        uiState = UiState.FAIL,
                        errorMessage = e.message ?: "",
                        isErrorFromProfileFetch = true,
                    )
                }
            }
        }
    }

    fun onTapLogout() {
        viewModelScope.launch {
            _state.update {
                it.copy(uiState = UiState.LOADING)
            }
            try {
                authRepository.clearInformationFromDatabase();
                _state.update {
                    it.copy(uiState = UiState.SUCCESS, auth = null, isErrorFromOnTapLogout = false)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        uiState = UiState.FAIL,
                        errorMessage = e.message ?: "",
                        isErrorFromOnTapLogout = true,
                    )
                }
            }
        }
    }
}
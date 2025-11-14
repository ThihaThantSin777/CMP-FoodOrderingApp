package org.thiha.thant.sin.foa.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.thiha.thant.sin.foa.home.data.HomeRepository
import org.thiha.thant.sin.foa.home.state.HomeState

class HomeViewModel : ViewModel() {
    val homeRepository: HomeRepository = HomeRepository;

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val restaurants = homeRepository.getAllRestaurant();
            _state.update {
                it.copy(restaurantList = restaurants)
            }
        }
    }
}
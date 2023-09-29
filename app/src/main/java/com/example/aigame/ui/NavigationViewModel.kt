package com.example.aigame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.util.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NavigationViewModel : ViewModel() {
    private val _stateNavigation = MutableStateFlow<Navigation>(Navigation.MENU)
    val stateNavigation: StateFlow<Navigation> = _stateNavigation
    fun loadState(navigation: Navigation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateNavigation.emit(navigation)
        }
    }
}
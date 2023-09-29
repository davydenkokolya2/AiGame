package com.example.aigame.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val _stateMenu = MutableStateFlow<Int>(1000000)
    val stateMenu: StateFlow<Int> = _stateMenu
    fun loadState(money: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMenu.emit(money)
        }
    }
}
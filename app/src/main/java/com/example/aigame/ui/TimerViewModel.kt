package com.example.aigame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {
    private val _stateTimer = MutableStateFlow<String>("")
    val stateTimer: StateFlow<String> = _stateTimer
    fun loadState(timer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateTimer.emit(timer)
        }
    }
}
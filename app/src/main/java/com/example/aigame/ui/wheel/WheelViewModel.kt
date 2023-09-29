package com.example.aigame.ui.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.ui.Wheel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WheelViewModel : ViewModel() {
    private val _stateWheel = MutableStateFlow<Wheel>(Wheel.SPIN)
    val stateWheel: StateFlow<Wheel> = _stateWheel
    fun loadState(wheel: Wheel) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateWheel.emit(wheel)
        }
    }
}
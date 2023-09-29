package com.example.aigame.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.util.Info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {
    private val _stateInfo = MutableStateFlow<Info>(Info.ABOUT)
    val stateInfo: StateFlow<Info> = _stateInfo
    fun loadState(info: Info) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateInfo.emit(info)
        }
    }
}
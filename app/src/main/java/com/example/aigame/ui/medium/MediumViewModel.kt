package com.example.aigame.ui.medium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.RoomCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MediumViewModel : ViewModel() {
    private val _stateMedium = MutableStateFlow<MutableList<RoomCardModel>>(mutableListOf())
    val stateMedium: StateFlow<MutableList<RoomCardModel>> = _stateMedium
    fun loadState(medium: MutableList<RoomCardModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMedium.emit(medium)
        }
    }
}
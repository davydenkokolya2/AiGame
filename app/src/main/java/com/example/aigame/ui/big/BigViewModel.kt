package com.example.aigame.ui.big

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.RoomCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BigViewModel : ViewModel() {
    private val _stateBig = MutableStateFlow<MutableList<RoomCardModel>>(mutableListOf())
    val stateBig: StateFlow<MutableList<RoomCardModel>> = _stateBig
    fun loadState(big: MutableList<RoomCardModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateBig.emit(big)
        }
    }
}
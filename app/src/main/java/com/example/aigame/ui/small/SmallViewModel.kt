package com.example.aigame.ui.small

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.domain.ShopCardModel
import com.example.aigame.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SmallViewModel : ViewModel() {
    private val _stateSmall = MutableStateFlow<MutableList<RoomCardModel>>(mutableListOf())
    val stateSmall: StateFlow<MutableList<RoomCardModel>> = _stateSmall
    fun loadState(small: MutableList<RoomCardModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSmall.emit(small)
        }
    }
}
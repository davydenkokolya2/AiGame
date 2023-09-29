package com.example.aigame.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.util.Shop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {
    private val _stateShop = MutableStateFlow<Shop>(Shop.FURNITURE)
    val stateShop: StateFlow<Shop> = _stateShop
    fun loadState(shop: Shop) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateShop.emit(shop)
        }
    }
}
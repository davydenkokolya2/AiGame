package com.example.aigame.ui.ShopPopUp_pop_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.ShopCardModel
import com.example.aigame.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopPopUpViewModel : ViewModel() {
    private val _stateShopPopUp = MutableStateFlow<ShopCardModel>(Constant.furniture[0])
    val stateShopPopUp: StateFlow<ShopCardModel> = _stateShopPopUp
    fun loadState(shopPopUp: ShopCardModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateShopPopUp.emit(shopPopUp)
        }
    }
}
package com.example.aigame.ui.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.CollectionCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GetViewModel : ViewModel() {
    private val _stateGet = MutableStateFlow<CollectionCardModel?>(null)
    val stateGet: StateFlow<CollectionCardModel?> = _stateGet
    fun loadState(get: CollectionCardModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateGet.emit(get)
        }
    }
}
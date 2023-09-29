package com.example.aigame.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CollectionViewModel : ViewModel() {
    private val _stateCollection = MutableStateFlow<Navigation>(Navigation.SMALL)
    val stateCollection: StateFlow<Navigation> = _stateCollection
    fun loadState(collection: Navigation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateCollection.emit(collection)
        }
    }

    private val _stateCollectionNumber = MutableStateFlow<Int>(0)
    val stateCollectionNumber: StateFlow<Int> = _stateCollectionNumber
    fun loadStateNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateCollectionNumber.emit(number)
        }
    }
}
package com.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.model.data.RelatedTopic
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel(){

    private val _searchQuery = MutableLiveData<String>()
    private val _characterData = MutableSharedFlow<RelatedTopic>()
    val searchQuery: LiveData<String> get() = _searchQuery
    val characterData: SharedFlow<RelatedTopic> get() = _characterData

    fun setSearchQuery(query: String){
        _searchQuery.postValue(query)
    }

    fun setCharacterData(character: RelatedTopic){
        viewModelScope.launch {
            _characterData.emit(character)
        }
    }
}

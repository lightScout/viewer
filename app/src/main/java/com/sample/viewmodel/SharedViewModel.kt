package com.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.model.data.RelatedTopic

class SharedViewModel: ViewModel(){

    private val _searchQuery = MutableLiveData<String>()
    private val _characterData = MutableLiveData<RelatedTopic>()
    val searchQuery: LiveData<String> get() = _searchQuery
    val characterData: LiveData<RelatedTopic> get() = _characterData

    fun setSearchQuery(query: String){
        _searchQuery.postValue(query)
    }

    fun setCharacterData(character: RelatedTopic){
        _characterData.postValue(character)
    }
}

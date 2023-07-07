package com.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.BuildConfig
import com.sample.model.network.interactor.CharactersInteractor
import com.sample.util.Constants.Companion.SIMPSONS_FLAVOR
import com.sample.util.Constants.Companion.SIMPSONS_QUERY
import com.sample.util.Constants.Companion.THE_WIRE_QUERY
import kotlinx.coroutines.launch


class CharactersListViewModel(private val charactersInteractor: CharactersInteractor) :
    ViewModel() {

    private val _charactersListState = MutableLiveData<CharactersListViewModelState>()
    internal val charactersListState: LiveData<CharactersListViewModelState> get() = _charactersListState

    init {
        if (BuildConfig.FLAVOR == SIMPSONS_FLAVOR)
            getCharacters(SIMPSONS_QUERY)
        else
            getCharacters(THE_WIRE_QUERY)
    }

    private fun getCharacters(query: String) {
        if (_charactersListState.value is CharactersListViewModelState.Loading) return
        viewModelScope.launch {
            _charactersListState.value = CharactersListViewModelState.Loading
            charactersInteractor.getCharacter(query).let { result ->
                when (result) {
                    is CharactersInteractor.GetCharactersResult.Success -> {
                        _charactersListState.value =
                            CharactersListViewModelState.ShowListOfCharacters(result.characters.RelatedTopics)
                    }
                    is CharactersInteractor.GetCharactersResult.Error -> {
                        _charactersListState.value =
                            CharactersListViewModelState.Error(result.exception)
                    }
                }
            }
        }

    }


}
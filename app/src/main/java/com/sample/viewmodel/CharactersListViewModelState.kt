package com.sample.viewmodel

import com.sample.model.data.RelatedTopic
import com.sample.model.network.error.ErrorViewModel

internal sealed class CharactersListViewModelState {
    object Loading : CharactersListViewModelState()
    data class ShowListOfCharacters(val characters: List<RelatedTopic>) : CharactersListViewModelState()
    data class Error(val exception: ErrorViewModel) : CharactersListViewModelState()
}
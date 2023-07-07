package com.sample.viewmodel

import com.sample.model.data.RelatedTopic

internal sealed class CharactersListViewModelState {
    object Loading : CharactersListViewModelState()
    data class showListOfCharacters(val characters: List<RelatedTopic>) : CharactersListViewModelState()
}
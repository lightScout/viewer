package com.sample.model.network.interactor

import com.sample.model.data.CharactersData
import com.sample.model.network.error.ErrorViewModel

interface CharactersInteractor {

    suspend fun getCharacter(
        query: String,
        format: String = "json"
    ): GetCharactersResult

    sealed class GetCharactersResult {
        data class Success(val characters: CharactersData) : GetCharactersResult()
        data class Error(val exception: ErrorViewModel) : GetCharactersResult()
    }

}
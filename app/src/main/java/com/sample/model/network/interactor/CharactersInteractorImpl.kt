package com.sample.model.network.interactor

import com.sample.model.data.CharactersData
import com.sample.model.network.error.ErrorViewMode
import com.sample.model.network.service.DuckDuckGoService

class CharactersInteractorImpl(private val service: DuckDuckGoService) : CharactersInteractor {

    override suspend fun getCharacter(
        query: String,
        format: String
    ): CharactersInteractor.GetCharactersResult {
        return try {
            val response = service.getService().getCharacters(query, format)
            response.toResponse()
        } catch (error: Throwable) {
            CharactersInteractor.GetCharactersResult.Error(error.toErrorViewModel())
        }
    }
}

private fun Throwable.toErrorViewModel(): ErrorViewMode {
    return ErrorViewMode(
        errorMessage = this.message ?: "Unknown error"
    )
}

private fun CharactersData.toResponse(): CharactersInteractor.GetCharactersResult {
    return CharactersInteractor.GetCharactersResult.Success(this)
}

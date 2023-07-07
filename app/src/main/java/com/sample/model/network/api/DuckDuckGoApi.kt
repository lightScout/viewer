package com.sample.model.network.api

import com.sample.model.data.CharactersData
import com.sample.util.Constants.Companion.FORMAT
import com.sample.util.Constants.Companion.QUERY
import retrofit2.http.GET
import retrofit2.http.Query

interface DuckDuckGoApi {
    @GET("/")
    suspend fun getCharacters(
        @Query(QUERY) query: String,
        @Query(FORMAT) format: String
    ): CharactersData
}
package com.sample.model.network.service

import com.sample.model.data.CharactersData
import com.sample.util.Constants.Companion.FORMAT
import com.sample.util.Constants.Companion.QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DuckDuckGoService {
    @GET("/")
    suspend fun getCharacters(
        @Query(QUERY) query: String,
        @Query(FORMAT) format: String
    ): Response<CharactersData>
}
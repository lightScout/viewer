package com.sample.model.network.service

import com.sample.model.network.api.DuckDuckGoApi
import com.sample.model.network.retrofit.RetrofitFactory

class DuckDuckGoService(private val retrofitFactory: RetrofitFactory) {
    fun getService():DuckDuckGoApi = retrofitFactory.createRetrofit().create(DuckDuckGoApi::class.java)
}
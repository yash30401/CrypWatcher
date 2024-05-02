package com.yash.crypwatcher.data.remote

import retrofit2.http.GET

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins()
}
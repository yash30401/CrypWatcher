package com.yash.crypwatcher.domain.repository

import com.yash.crypwatcher.data.remote.dto.CoinDetailDto
import com.yash.crypwatcher.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coinId:String):CoinDetailDto
}
package com.yash.crypwatcher.data.repository

import com.yash.crypwatcher.data.remote.CoinApi
import com.yash.crypwatcher.data.remote.dto.CoinDetailDto
import com.yash.crypwatcher.data.remote.dto.CoinDto
import com.yash.crypwatcher.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
):CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinApi.getCoinById(coinId)
    }
}
package com.yash.crypwatcher.domain.use_case.get_coins

import coil.network.HttpException
import com.yash.crypwatcher.data.remote.dto.toCoin
import com.yash.crypwatcher.domain.model.Coin
import com.yash.crypwatcher.domain.repository.CoinRepository
import com.yash.crypwatcher.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<Coin>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val coins = repository.getCoins().map {
                it.toCoin()
            }
            emit(NetworkResult.Success(coins))
        }catch (e:HttpException){
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected Error Occured"))
        }catch (e:IOException){
            emit(NetworkResult.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
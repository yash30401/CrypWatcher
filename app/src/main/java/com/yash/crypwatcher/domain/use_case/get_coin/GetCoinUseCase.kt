package com.yash.crypwatcher.domain.use_case.get_coin

import coil.network.HttpException
import com.yash.crypwatcher.data.remote.dto.toCoinDetail
import com.yash.crypwatcher.domain.model.CoinDetail
import com.yash.crypwatcher.domain.repository.CoinRepository
import com.yash.crypwatcher.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId:String): Flow<NetworkResult<CoinDetail>> = flow {
        try {
            emit(NetworkResult.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(NetworkResult.Success(coin))
        }catch (e:HttpException){
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected Error Occured"))
        }catch (e:IOException){
            emit(NetworkResult.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
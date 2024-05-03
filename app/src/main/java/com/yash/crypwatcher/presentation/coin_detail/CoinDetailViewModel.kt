package com.yash.crypwatcher.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.crypwatcher.domain.use_case.get_coin.GetCoinUseCase
import com.yash.crypwatcher.domain.use_case.get_coins.GetCoinsUseCase
import com.yash.crypwatcher.utils.Constants
import com.yash.crypwatcher.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId:String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An Unknown Error Occured")
                }

                is NetworkResult.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
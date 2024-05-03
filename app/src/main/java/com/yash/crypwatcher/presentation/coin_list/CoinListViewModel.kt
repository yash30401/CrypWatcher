package com.yash.crypwatcher.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.crypwatcher.domain.use_case.get_coins.GetCoinsUseCase
import com.yash.crypwatcher.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value =
                        CoinListState(error = result.message ?: "An Unknown Error Occured")
                }

                is NetworkResult.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}
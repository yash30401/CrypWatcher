package com.yash.crypwatcher.presentation.coin_list

import com.yash.crypwatcher.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
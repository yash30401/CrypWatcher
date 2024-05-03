package com.yash.crypwatcher.presentation.coin_detail

import com.yash.crypwatcher.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin:CoinDetail? =null,
    val error:String= ""
)

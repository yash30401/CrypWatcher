package com.yash.crypwatcher.presentation

sealed class Screen(val route:String) {
    object CoinListScreen:Screen("coin_list_screen")
    object CoinDetailScreen:Screen("coint_detail_screen")
}
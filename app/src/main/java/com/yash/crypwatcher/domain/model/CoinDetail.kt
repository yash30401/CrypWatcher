package com.yash.crypwatcher.domain.model

import com.yash.crypwatcher.data.remote.dto.TeamMember

data class CoinDetail(
    val logo:String,
    val coinId:String,
    val name:String,
    val description:String,
    val symbol:String,
    val rank:Int,
    val isActive:Boolean,
    val tags:List<String>,
    val team:List<TeamMember>
)

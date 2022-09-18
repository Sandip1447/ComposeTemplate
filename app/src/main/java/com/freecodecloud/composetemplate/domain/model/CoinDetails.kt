package com.freecodecloud.composetemplate.domain.model

import com.freecodecloud.composetemplate.data.remote.dto.TeamMember

data class CoinDetails(
    val coinId: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>,
)

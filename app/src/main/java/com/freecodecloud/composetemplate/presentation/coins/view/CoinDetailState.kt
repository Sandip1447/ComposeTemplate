package com.freecodecloud.composetemplate.presentation.coins.view

import com.freecodecloud.composetemplate.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val message: String = "",
    val coin: CoinDetails? = null
)

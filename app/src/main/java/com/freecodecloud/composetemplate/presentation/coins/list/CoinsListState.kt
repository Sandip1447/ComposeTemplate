package com.freecodecloud.composetemplate.presentation.coins.list

import com.freecodecloud.composetemplate.domain.model.Coin

data class CoinsListState(
    val isLoading: Boolean = false,
    val message: String = "",
    val coins: List<Coin> = emptyList(),
)

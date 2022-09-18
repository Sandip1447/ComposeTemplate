package com.freecodecloud.composetemplate.presentation.coins.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecodecloud.composetemplate.common.Constants
import com.freecodecloud.composetemplate.common.Resource
import com.freecodecloud.composetemplate.domain.use_case.coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinById(coinId = coinId)
        }
    }

    private fun getCoinById(coinId: String) {

        getCoinUseCase(coinId).onEach { result ->

            when (result) {
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        isLoading = false,
                        message = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> _state.value = CoinDetailState(isLoading = true)
                is Resource.Success -> {
                    _state.value =
                        CoinDetailState(isLoading = false, coin = result.data)
                }
            }

        }.launchIn(viewModelScope)

    }
}
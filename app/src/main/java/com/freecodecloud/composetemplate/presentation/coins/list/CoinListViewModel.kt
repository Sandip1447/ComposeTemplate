package com.freecodecloud.composetemplate.presentation.coins.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecodecloud.composetemplate.common.Resource
import com.freecodecloud.composetemplate.domain.use_case.coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinsListState())
    val state: State<CoinsListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {

        getCoinsUseCase().onEach { result ->

            when (result) {
                is Resource.Error -> {
                    _state.value = CoinsListState(
                        isLoading = false,
                        message = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> _state.value = CoinsListState(isLoading = true)
                is Resource.Success -> {
                    _state.value =
                        CoinsListState(isLoading = false, coins = result.data ?: emptyList())
                }
            }

        }.launchIn(viewModelScope)

    }

}
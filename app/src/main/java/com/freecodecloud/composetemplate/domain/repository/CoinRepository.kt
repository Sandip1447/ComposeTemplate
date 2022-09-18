package com.freecodecloud.composetemplate.domain.repository

import com.freecodecloud.composetemplate.data.remote.CoinPaprikaApi
import com.freecodecloud.composetemplate.data.remote.dto.CoinDetailsDto
import com.freecodecloud.composetemplate.data.remote.dto.CoinDto
import javax.inject.Inject

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailsDto

}

class CoinRepositoryImpl @Inject constructor(
    private val webApi: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return webApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return webApi.getCoinById(coinId)
    }

}

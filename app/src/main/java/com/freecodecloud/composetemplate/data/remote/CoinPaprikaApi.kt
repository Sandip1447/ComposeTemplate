package com.freecodecloud.composetemplate.data.remote

import com.freecodecloud.composetemplate.data.remote.dto.CoinDetailsDto
import com.freecodecloud.composetemplate.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailsDto
}
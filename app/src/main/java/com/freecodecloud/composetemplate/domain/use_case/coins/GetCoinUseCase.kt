package com.freecodecloud.composetemplate.domain.use_case.coins

import com.freecodecloud.composetemplate.common.Resource
import com.freecodecloud.composetemplate.data.remote.dto.toCoinDetails
import com.freecodecloud.composetemplate.domain.model.CoinDetails
import com.freecodecloud.composetemplate.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {

        try {

            emit(Resource.Loading<CoinDetails>())

            val coinDetails = repository.getCoinById(coinId).toCoinDetails()

            emit(Resource.Success<CoinDetails>(coinDetails))

        } catch (e: HttpException) {

            emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetails>("Couldn't reached server. Check your internet connection."))
        }
    }
}
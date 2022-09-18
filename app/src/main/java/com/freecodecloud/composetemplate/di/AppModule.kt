package com.freecodecloud.composetemplate.di

import com.freecodecloud.composetemplate.common.Constants
import com.freecodecloud.composetemplate.data.remote.CoinPaprikaApi
import com.freecodecloud.composetemplate.domain.repository.CoinRepository
import com.freecodecloud.composetemplate.domain.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        paprikaApi: CoinPaprikaApi
    ): CoinRepository {
        return CoinRepositoryImpl(
            paprikaApi
        )
    }
}
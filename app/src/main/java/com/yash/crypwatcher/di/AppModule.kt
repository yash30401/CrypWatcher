package com.yash.crypwatcher.di

import com.yash.crypwatcher.data.remote.CoinApi
import com.yash.crypwatcher.data.repository.CoinRepositoryImpl
import com.yash.crypwatcher.domain.repository.CoinRepository
import com.yash.crypwatcher.utils.Constants.BASE_URL
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
    fun provideCoinApi():CoinApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:CoinApi):CoinRepository{
        return CoinRepositoryImpl(api)
    }
}
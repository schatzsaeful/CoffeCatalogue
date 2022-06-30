package com.example.moviecatalogue.network

import android.content.Context
import com.example.coffeecatalogue.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkProvider(
        @ApplicationContext context: Context
    ): NetworkProvider {
        return NetworkProvider(context, BuildConfig.BASE_URL)
    }

    @Singleton
    @Provides
    fun provideRetrofit(networkProvider: NetworkProvider): Retrofit {
        return networkProvider.createRetrofit()
    }
}

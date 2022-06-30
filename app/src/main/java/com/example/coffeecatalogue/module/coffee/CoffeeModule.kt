package com.example.coffeecatalogue.module.coffee

import com.example.coffeecatalogue.module.coffee.contract.CoffeeRepositoryContract
import com.example.coffeecatalogue.network.api.CoffeeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CoffeeModule {

    @Provides
    fun provideCoffeeRepository(
        retrofit: Retrofit
    ): CoffeeRepositoryContract {
        return CoffeeRepository(retrofit.create(CoffeeApiService::class.java))
    }
}
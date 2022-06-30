package com.example.coffeecatalogue.network.api

import com.example.coffeecatalogue.model.entity.CoffeeEntity
import retrofit2.http.GET

interface CoffeeApiService {

    @GET("coffee/hot")
    suspend fun getCoffeeHot(): CoffeeEntity

}
package com.example.coffeecatalogue.module.coffee.contract

import com.example.coffeecatalogue.model.entity.CoffeeEntity
import com.example.coffeecatalogue.network.response.ApiResult

interface CoffeeRepositoryContract {

    suspend fun onRequestCoffeeList() : ApiResult<CoffeeEntity>
}
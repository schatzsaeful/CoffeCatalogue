package com.example.coffeecatalogue.module.coffee

import com.example.coffeecatalogue.model.entity.CoffeeEntity
import com.example.coffeecatalogue.module.coffee.contract.CoffeeRepositoryContract
import com.example.coffeecatalogue.network.api.CoffeeApiService
import com.example.coffeecatalogue.network.response.ApiResult
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val coffeeApiService: CoffeeApiService
) : CoffeeRepositoryContract {

    override suspend fun onRequestCoffeeList(): ApiResult<CoffeeEntity> {
        return try {
            val response = coffeeApiService.getCoffeeHot()
            return ApiResult.Success(response)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

}
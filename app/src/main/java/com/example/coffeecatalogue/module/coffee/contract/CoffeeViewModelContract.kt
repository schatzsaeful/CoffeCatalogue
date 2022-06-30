package com.example.coffeecatalogue.module.coffee.contract

import androidx.lifecycle.LiveData
import com.example.coffeecatalogue.model.viewparam.CoffeeViewParam

interface CoffeeViewModelContract {

    fun observeProgress(): LiveData<Boolean>

    fun observeError(): LiveData<String>

    fun observeListCoffeeHot(): LiveData<List<CoffeeViewParam.Data>>

}
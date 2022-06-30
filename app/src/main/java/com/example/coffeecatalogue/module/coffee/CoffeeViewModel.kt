package com.example.coffeecatalogue.module.coffee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coffeecatalogue.base.BaseViewModel
import com.example.coffeecatalogue.model.entity.CoffeeEntity
import com.example.coffeecatalogue.model.viewparam.CoffeeViewParam
import com.example.coffeecatalogue.module.coffee.contract.CoffeeRepositoryContract
import com.example.coffeecatalogue.module.coffee.contract.CoffeeViewModelContract
import com.example.coffeecatalogue.network.response.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val repository: CoffeeRepositoryContract
) : BaseViewModel(), CoffeeViewModelContract {

    private val progressLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<String>()
    private val coffeeListLiveData = MutableLiveData<List<CoffeeViewParam.Data>>()

    fun doRequestCoffeeList() {
        progressLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO + getSupervisorJob()) {
            when (val result = repository.onRequestCoffeeList()) {
                is ApiResult.Success -> {
                    progressLiveData.postValue(false)
                    coffeeListLiveData.postValue(convertEntityToViewParam(result.results))
                }
                is ApiResult.Error -> {
                    progressLiveData.postValue(false)
                }
            }
        }
    }

    private fun convertEntityToViewParam(
        entity: List<CoffeeEntity.Data>?
    ): List<CoffeeViewParam.Data> {
        val list = mutableListOf<CoffeeViewParam.Data>()
        entity?.forEach { it ->
            list.add(
                CoffeeViewParam.Data(
                    description = it.description.orEmpty(),
                    id = it.id ?: 0,
                    image = it.image.orEmpty(),
                    ingredients = it.ingredients.orEmpty(),
                    title = it.title.orEmpty()
                )
            )
        }
        return list
    }

    override fun observeProgress(): LiveData<Boolean> = progressLiveData

    override fun observeError(): LiveData<String> = errorLiveData

    override fun observeListCoffeeHot(): LiveData<List<CoffeeViewParam.Data>> = coffeeListLiveData

}
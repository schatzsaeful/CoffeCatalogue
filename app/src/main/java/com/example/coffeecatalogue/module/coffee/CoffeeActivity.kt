package com.example.coffeecatalogue.module.coffee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.coffeecatalogue.R
import com.example.coffeecatalogue.base.BaseActivity
import com.example.coffeecatalogue.databinding.ActivityCoffeeBinding
import com.example.coffeecatalogue.utils.reObserve
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class CoffeeActivity : BaseActivity<ActivityCoffeeBinding>() {

    private val viewModel: CoffeeViewModel by viewModels()
    private val coffeeAdapter by lazy { CoffeeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        subscribeLiveData()
    }

    private fun initView() {
        with(getViewBinder()) {
            with(rvCoffeeHot) {
                adapter = coffeeAdapter
            }
        }
    }

    private fun subscribeLiveData() {
        with(viewModel) {
            doRequestCoffeeList()

            observeListCoffeeHot().reObserve(this@CoffeeActivity) { data ->
                val resultData = data.sortedByDescending { it.id }
                coffeeAdapter.setupList(resultData)
            }

            observeProgress().reObserve(this@CoffeeActivity) { isLoad ->

            }

            observeError().reObserve(this@CoffeeActivity) { message ->
                Log.d("afsdqrwe123", "message: $message")
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_coffee
}
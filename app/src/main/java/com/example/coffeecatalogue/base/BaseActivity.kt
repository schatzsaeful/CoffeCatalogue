package com.example.coffeecatalogue.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<out T : ViewDataBinding> : AppCompatActivity() {

    private lateinit var viewBinder: T

    protected fun getViewBinder() = viewBinder

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performViewBinding()
    }

    private fun performViewBinding() {
        viewBinder = DataBindingUtil.setContentView(this, getLayoutId())
        viewBinder.executePendingBindings()
    }

}
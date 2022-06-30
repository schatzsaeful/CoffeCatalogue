package com.example.coffeecatalogue

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoffeeCatalogueApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
package com.example.moviecatalogue.network

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.coffeecatalogue.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class NetworkProvider(
    private val context: Context,
    private val url: String
) {
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    fun createOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val original = chain.request()
                val currentTimezone = SimpleDateFormat("Z", Locale.getDefault()).format(System.currentTimeMillis())
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }

        if (BuildConfig.DEBUG) {
            httpBuilder
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(getChuckerInterceptor())
        }

        return httpBuilder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("Interceptor", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun getChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}

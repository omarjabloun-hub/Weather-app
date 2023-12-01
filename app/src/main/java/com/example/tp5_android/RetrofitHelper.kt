package com.example.tp5_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private const val apiKey = "17db59488cadcad345211c36304a9266"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService: WeatherAPI by lazy { retrofit.create(WeatherAPI::class.java) }

    fun getWeatherService1(): WeatherAPI {
        return weatherService
    }

    fun getApiKey(): String {
        return apiKey
    }
}


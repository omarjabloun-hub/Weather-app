package com.example.tp5_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()

    fun getWeatherData(): LiveData<WeatherResponse> {
        return weatherRepository.getWeatherData()
    }
}

package com.example.tp5_android

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()

    fun getWeatherData(cityName: String,context:Context): LiveData<WeatherResponse> {
        return weatherRepository.getWeatherData(cityName,context)
    }

}

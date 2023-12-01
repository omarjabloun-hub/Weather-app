package com.example.tp5_android


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ForecastViewModel: ViewModel() {
    private val weatherRepository = WeatherRepository()

    fun getForecastData(cityId: Int,context: Context): LiveData<ForecastResponse> {
        return weatherRepository.getForecastData(cityId,context)
    }
}
package com.example.tp5_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val weatherService = RetrofitHelper.retrofitService

    fun getWeatherData(): LiveData<WeatherResponse> {
        val weatherData = MutableLiveData<WeatherResponse>()

        weatherService.getWeather().enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })

        return weatherData
    }
}

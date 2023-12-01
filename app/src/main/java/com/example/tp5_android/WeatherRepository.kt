package com.example.tp5_android

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val weatherService = RetrofitHelper.weatherService

    fun getWeatherData(cityName: String,context: Context ): LiveData<WeatherResponse> {
        val weatherData = MutableLiveData<WeatherResponse>()

        weatherService.getWeather(cityName, RetrofitHelper.getApiKey())
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        weatherData.value = response.body()
                    } else{
                        Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                }
            })

        return weatherData
    }
}

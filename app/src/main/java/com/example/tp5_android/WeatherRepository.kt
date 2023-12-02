package com.example.tp5_android

import android.content.Context
import android.util.Log
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
                    Log.d("response:",response.toString())
                    if (response.isSuccessful) {
                        weatherData.value = response.body()
                        Log.d("response succ: ",response.body().toString())
                    } else{
                        Log.e("response error: ", response.message())
                        Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("network failure: ", t.message, t)
                    Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                }
            })

        return weatherData
    }

    fun getForecastData(cityId: Int,context: Context ): LiveData<ForecastResponse> {
        val forecastData = MutableLiveData<ForecastResponse>()

        weatherService.getForecast(cityId, RetrofitHelper.getApiKey())
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        forecastData.value = response.body()
                    } else{
                        Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Toast.makeText(context, "Network request failed", Toast.LENGTH_SHORT).show()
                }
            })

        return forecastData
    }

}

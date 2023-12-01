package com.example.tp5_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("APPID") apiKey: String): Call<WeatherResponse>

    @GET("forecast")
    fun getForecast(@Query("id") cityId: Int, @Query("APPID") apiKey: String): Call<ForecastResponse>
}

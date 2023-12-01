package com.example.tp5_android

import retrofit2.Call
import retrofit2.http.GET

interface WeatherAPI {

    @GET("weather?q=Tunis&APPID=17db59488cadcad345211c36304a9266")
    fun getWeather() : Call<WeatherResponse>
}

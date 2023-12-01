package com.example.tp5_android

data class elemList (
    val clouds: CloudsX,
    val dt: Int,
    val dt_txt: String,
    val main: MainX,
    val snow: Snow,
    val sys: SysX,
    val weather: List<WeatherX>,
    val wind: WindX
)
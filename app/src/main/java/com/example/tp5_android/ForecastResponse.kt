package com.example.tp5_android

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<>,
    val message: Double
)
package com.example.tp5_android

data class ForecastItem(
    val dateTime: String, // Date and time of the forecast
    val weatherIconUrl: String, // URL for the weather icon
    val weatherDescription: String, // Description of the weather (e.g., cloudy, sunny, etc.)
    val temperature: Double, // Temperature value
    val humidity: Int, // Humidity value
    val windSpeed: Double // Wind speed value
)


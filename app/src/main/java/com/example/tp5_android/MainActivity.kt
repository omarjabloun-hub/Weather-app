package com.example.tp5_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // Observe weather data changes
        viewModel.getWeatherData().observe(this, { weatherResponse ->
            // Update UI with weather data
            displayWeatherData(weatherResponse)
        })
    }

    private fun displayWeatherData(weatherResponse: WeatherResponse) {
        // Update TextViews with weather details
        findViewById<TextView>(R.id.tempText).text = "${weatherResponse.main.temp-273.15} ℃" // Adjust temperature format
        findViewById<TextView>(R.id.overCastText).text = weatherResponse.weather[0].description
        findViewById<TextView>(R.id.humidityTextView2).text = "${weatherResponse.main.humidity}%"
        findViewById<TextView>(R.id.pressureTextView).text = "${weatherResponse.main.pressure} hPa"
        findViewById<TextView>(R.id.cityText).text = weatherResponse.name

        // Update ImageView based on weather conditions (Replace this with your logic)
        val imageView = findViewById<ImageView>(R.id.imageView)
        if (weatherResponse.weather[0].main.contains("Clouds", ignoreCase = true)) {
            imageView.setImageResource(R.drawable.good)
        } else {
            imageView.setImageResource(R.drawable.good)
        }
    }
}

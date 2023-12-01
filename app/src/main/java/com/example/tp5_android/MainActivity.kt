package com.example.tp5_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val citySpinner = findViewById<Spinner>(R.id.citySpinner)
        val cities = resources.getStringArray(R.array.city_array)

        citySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

        val apiKey = RetrofitHelper.getApiKey()
        val weatherService = RetrofitHelper.getWeatherService1()

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = cities[position]
                fetchWeatherData(selectedCity)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

    }

    private fun fetchWeatherData(cityName: String) {
        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherViewModel.getWeatherData(cityName,this).observe(this) {wheatherResponse ->
            displayWeatherData(wheatherResponse)
            Toast.makeText(this, "Weather data fetched successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayWeatherData(weatherResponse: WeatherResponse?) {
        // Update UI with weather data
        // Example: Update TextViews with weather details
        weatherResponse?.let {
            findViewById<TextView>(R.id.tempText).text = "${convertKelvinToCelsius(it.main.temp).roundToInt()} \u2103" // Temperature in Celsius
            findViewById<TextView>(R.id.overCastText).text = it.weather[0].description
            findViewById<TextView>(R.id.humidityTextView2).text = "${it.main.humidity}%"
            findViewById<TextView>(R.id.pressureTextView).text = "${it.main.pressure} hPa"
            findViewById<TextView>(R.id.cityText).text = it.name
        }
    }

    private fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }
}

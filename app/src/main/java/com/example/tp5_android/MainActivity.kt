package com.example.tp5_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    var selectedCityId:Int = 2464461 // Tunisia by default
    private lateinit var weatherViewModel: WeatherViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val citySpinner = findViewById<Spinner>(R.id.citySpinner)
        val cities = resources.getStringArray(R.array.city_array)

        citySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)


        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = cities[position]
                Log.d("selectedCity: ",selectedCity)
                fetchWeatherData(selectedCity)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        val buttonGoToForecastActivity = findViewById<Button>(R.id.button)
        buttonGoToForecastActivity.setOnClickListener {
            val intent = Intent(this, WeatherForecastActivity::class.java)
            intent.putExtra("cityId", selectedCityId)
            startActivity(intent)
        }
    }

    private fun fetchWeatherData(cityName: String) {
        Log.d("fetching","ho")
        weatherViewModel.getWeatherData(cityName,this).observe(this) {wheatherResponse ->
            Log.d("weather response: ",wheatherResponse.toString())
            displayWeatherData(wheatherResponse)
            selectedCityId = wheatherResponse.id
            Log.d("id: ",selectedCityId.toString())
            Toast.makeText(this, "Weather data fetched successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayWeatherData(weatherResponse: WeatherResponse?) {
        weatherResponse?.let {
            findViewById<TextView>(R.id.tempText).text = "${convertKelvinToCelsius(it.main.temp)} \u2103"
            findViewById<TextView>(R.id.mainText).text = it.weather[0].main
            findViewById<TextView>(R.id.overCastText).text = it.weather[0].description
            findViewById<TextView>(R.id.humidityTextView2).text = "${it.main.humidity}%"
            findViewById<TextView>(R.id.pressureTextView).text = "${it.main.pressure} hPa"
            findViewById<TextView>(R.id.cityText).text = it.name
            updateWeatherImage(it.weather[0].main)
        }
    }

    private fun convertKelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).roundToInt()
    }

    private fun updateWeatherImage(weatherCondition: String) {
        val imageView = findViewById<ImageView>(R.id.imageView)

        when (weatherCondition.toLowerCase()) {
            "clear" -> imageView.setImageResource(R.drawable.ic_clear_sky)
            "rain" -> imageView.setImageResource(R.drawable.rainy)
            "clouds" -> imageView.setImageResource(R.drawable.cloudy)

            // Default case (fallback image when condition is unknown)
            else -> imageView.setImageResource(R.drawable.ic_clear_sky)
        }
}
}

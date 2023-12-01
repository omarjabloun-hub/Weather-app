package com.example.tp5_android

import ForecastAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class WeatherForecastActivity : AppCompatActivity() {
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var forecastRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)
        val intent = intent
        val cityId = intent.getIntExtra("cityId", 2464461)
        val buttonGoToMainActivity = findViewById<Button>(R.id.buttonGoToMain)
        buttonGoToMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        forecastRecyclerView = findViewById(R.id.recyclerViewForecast)
        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        fetchForecastData(cityId)
    }

    private fun fetchForecastData(cityId: Int) {
        forecastViewModel.getForecastData(cityId,this).observe(this) { forecastResponse ->
            // Handle the received forecastResponse here, update RecyclerView or perform actions as needed
            if (forecastResponse != null) {
                // Assuming you have a RecyclerView and an adapter set up
                 // Replace with your data structure from ForecastResponse
                val forecastAdapter =
                    ForecastAdapter(this, forecastResponse) // Create or update your adapter

                // Update your RecyclerView adapter with the new data
                forecastRecyclerView.adapter = forecastAdapter
            }
        }
    }


}
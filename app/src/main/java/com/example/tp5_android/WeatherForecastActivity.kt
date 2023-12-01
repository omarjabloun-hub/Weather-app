package com.example.tp5_android

import ForecastAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WeatherForecastActivity : AppCompatActivity() {
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var forecastRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        val buttonGoToMainActivity = findViewById<Button>(R.id.buttonGoToMain)
        buttonGoToMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createDummyForecastList(): List<ForecastItem> {
        // Replace this with your actual list of forecast items from API response
        // This is a sample data creation; you should parse your JSON response here
        val forecastList = mutableListOf<ForecastItem>()
        // Add sample data for demonstration
        // Add as many ForecastItem objects as needed
        forecastList.add(
            ForecastItem(
                "2023-12-01 18:00:00",
                "268.77",
                "light snow",
                97.0,
                15,
                45.0,
            )
        )
        forecastList.add(
            ForecastItem(
                "2023-12-01 21:00:00",
                "267.56",
                "light snow",
                98.0,
                16,
                46.0,
            )
        )
        // Add more items as needed...

        return forecastList
    }
}
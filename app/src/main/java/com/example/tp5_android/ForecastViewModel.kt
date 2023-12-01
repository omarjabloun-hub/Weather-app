package com.example.tp5_android


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ForecastViewModel: ViewModel() {
    private val _forecastData = MutableLiveData<List<ForecastItem>>() // Replace ForecastItem with your data model
    val forecastData: LiveData<List<ForecastItem>> get() = _forecastData

    // Function to fetch forecast data
    fun fetchForecastData(cityName: String) {
        // Implement logic to fetch forecast data from API using Retrofit or other network libraries
        // Update _forecastData with the retrieved forecast data
        // Notify observers about the updated data using _forecastData.postValue()
    }
}
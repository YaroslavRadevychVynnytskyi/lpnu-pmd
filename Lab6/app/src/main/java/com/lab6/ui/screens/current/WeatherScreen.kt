package com.lab6.ui.screens.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherScreen(lat: Double, lon: Double, viewModel: WeatherScreenViewModel = getViewModel()) {
    // Collecting the weather response state
    val weatherResponse = viewModel.weatherResponseStateFlow.collectAsState().value

    // Load weather data when the screen is first displayed
    LaunchedEffect(lat, lon) {
        viewModel.loadWeather(lat, lon)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        weatherResponse?.let { weather ->
            Text("Temperature: ${weather.main.temp}°C", fontSize = 20.sp)
            Text("Feels like: ${weather.main.feels_like}°C", fontSize = 20.sp)
            Text("Humidity: ${weather.main.humidity}%", fontSize = 20.sp)
            Text("Pressure: ${weather.main.pressure}%", fontSize = 20.sp)
        } ?: run {
            // Show loading or error message
            Text("Loading weather data...", fontSize = 20.sp)
        }
    }
}
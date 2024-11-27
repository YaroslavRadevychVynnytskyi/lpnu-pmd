package com.lab6.ui.screens.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab6.data.ServerApi
import com.lab6.data.entity.response.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherScreenViewModel(
    private val serverModule: ServerApi // Server module - communication interface with API
) : ViewModel() {

    private val _weatherResponseStateFlow = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponseStateFlow: StateFlow<WeatherResponse?>
        get() = _weatherResponseStateFlow

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            val weatherResponse = serverModule.getCurrentWeather(lat, lon)
            _weatherResponseStateFlow.value = weatherResponse
        }
    }
}
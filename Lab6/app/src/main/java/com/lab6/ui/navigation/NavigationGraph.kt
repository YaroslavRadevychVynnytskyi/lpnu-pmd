package com.lab6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lab6.ui.screens.city.CitySelectionScreen
import com.lab6.ui.screens.current.WeatherScreen
import com.lab6.ui.screens.manu.MenuScreen

const val SCREEN_MENU_SCREEN = "menuScreen"
const val SCREEN_WEATHER_SCREEN = "weatherScreen"
const val SCREEN_WEATHER_FORECAST_SCREEN = "weatherForecastScreen"
const val SCREEN_CITY_SELECTION_SCREEN = "citySelectionScreen"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_MENU_SCREEN
    ) {
        composable(SCREEN_MENU_SCREEN) {
            MenuScreen(
                onWeatherForecast = { navController.navigate(SCREEN_WEATHER_FORECAST_SCREEN) },
                onCity = { navController.navigate(SCREEN_CITY_SELECTION_SCREEN) },
            )
        }
        composable(SCREEN_WEATHER_SCREEN + "/{lat}/{lon}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDouble() ?: 0.0
            val lon = backStackEntry.arguments?.getString("lon")?.toDouble() ?: 0.0
            WeatherScreen(lat = lat, lon = lon)
        }
        composable(SCREEN_CITY_SELECTION_SCREEN) {
            CitySelectionScreen(navController = navController)
        }
    }
}
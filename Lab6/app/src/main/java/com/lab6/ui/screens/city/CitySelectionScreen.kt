package com.lab6.ui.screens.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lab6.data.entity.City

@Composable
fun CitySelectionScreen(navController: NavHostController) {
    val cities = listOf(
        City("Kyiv", 50.4501, 30.5236),
        City("Lviv", 49.8397, 24.0297),
        City("Odessa", 46.4825, 30.7326),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select a City", fontSize = 22.sp, modifier = Modifier.fillMaxWidth())
        LazyColumn {
            items(cities) { city ->
                Button(
                    onClick = {
                        // Passing city name, latitude and longitude as route parameters
                        navController.navigate("weatherScreen/${city.lat}/${city.lon}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(city.name, fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}
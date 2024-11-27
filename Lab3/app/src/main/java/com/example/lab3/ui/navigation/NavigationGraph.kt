package com.example.lab3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lab3.ui.screens.placeDetails.PlaceDetailsScreen
import com.lab3.ui.screens.placesList.PlacesListScreen

// Constants with path (links) for screens
const val SCREEN_PLACE_LIST = "placeList"
const val SCREEN_PLACE_DETAILS = "placeDetails"

/**
 * NavigationGraph - composable function, is just a container with navigation logic
 * - [navController]: NavHostController - the object to make navigation requests (navigateTo(), navigateUp(), etc)
 */
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    /**
     * NavHost - container for navigation of many screens
     * - [startDestination] - the first screen which has to be shown
     */
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_PLACE_LIST
    ) {
        // composable - the container for certain screen
        composable(
            route = SCREEN_PLACE_LIST, // route - the path of this screen
        ) {
            // Invocation of screen function
            PlacesListScreen(
                onDetailsScreen = { id ->
                    // navigation to details screen with parameter ID
                    navController.navigate("$SCREEN_PLACE_DETAILS/$id")
                }
            )
        }

        composable(
            route = "$SCREEN_PLACE_DETAILS/{id}", // route - the path with parameter
            arguments = listOf(
                // arguments - declaration of arguments
                navArgument("id") {
                    type = NavType.Companion.IntType
                    nullable = false
                },
            )
        ) { backStack ->
            // backStack - includes info of arguments which was sent to this screen
            PlaceDetailsScreen(
                id = backStack.arguments?.getInt("id") ?: -1 // parsing of arguments to open screen with parameter ID
            )
        }
    }
}
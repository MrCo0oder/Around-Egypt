package com.example.aroundegypt.presentaion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.aroundegypt.presentaion.screens.details.DetailsScreen
import com.example.aroundegypt.presentaion.screens.home.HomeScreen

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Destinations.Home
    ) {
        composable<Destinations.Home> {
            HomeScreen {
                navController.navigate(Destinations.Details(it))
            }
        }
        composable<Destinations.Details>() {
            val args = it.toRoute<Destinations.Details>()
            DetailsScreen(args.id) {
                navController.popBackStack()
            }
        }
    }
}
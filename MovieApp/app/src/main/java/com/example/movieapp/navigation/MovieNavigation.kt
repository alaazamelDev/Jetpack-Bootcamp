package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HOME_SCREEN.name
    ) {

        // Home Screen route
        composable(MovieScreens.HOME_SCREEN.name) {
            // Here we pass where the route should lead us
            HomeScreen(navController = navController)
        }
        // Details Screen route
        composable(
            MovieScreens.DETAILS_SCREEN.name + "/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) {
            // Link it up with Details Screen
            DetailsScreen(
                navController = navController,
                movieData = it.arguments?.getString("movie")
            )
        }

    }
}
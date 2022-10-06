package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HOME_SCREEN.name
    ) {
        composable(MovieScreens.HOME_SCREEN.name) {
            // Here we pass where the route should lead us
//            HomeScreen()
        }
    }
}
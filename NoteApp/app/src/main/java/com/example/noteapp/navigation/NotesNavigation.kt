package com.example.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.screens.MainScreen

@Composable
fun NotesNavigation() {

    // Initialize controller
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NoteRoutes.MAIN_SCREEN.name) {
        composable(NoteRoutes.MAIN_SCREEN.name) {

            // Link route with MainScreen
            MainScreen(navController = navController)
        }
    }

}
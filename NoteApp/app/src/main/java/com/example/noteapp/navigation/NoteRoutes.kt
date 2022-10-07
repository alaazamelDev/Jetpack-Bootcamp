package com.example.noteapp.navigation

enum class NoteRoutes {
    MAIN_SCREEN;

    companion object {
        fun findByRouteName(route: String?): NoteRoutes = when (route) {
            MAIN_SCREEN.name -> NoteRoutes.MAIN_SCREEN
            null -> NoteRoutes.MAIN_SCREEN
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}
package com.example.movieapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.components.MovieCard
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = { TopBar() }) {
        MainContent(navController = navController)
    }
}


@Composable
fun MainContent(
    navController: NavController,
    moviesList: List<Movie> = Movie.getMoviesList()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = moviesList) {
                MovieCard(movie = it) { item ->
                    navController.navigate(route = MovieScreens.DETAILS_SCREEN.name + "/${item.id}") {
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(contentPadding = PaddingValues(horizontal = 16.dp)) {
        Text(
            text = "Movie App",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Medium
        )
    }
}
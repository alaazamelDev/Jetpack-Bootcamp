package com.example.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.models.Movie

@Composable
fun DetailsScreen(navController: NavController, movieID: Int?) {
    Scaffold(
        topBar = {
            TopAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Icon(
                    modifier = Modifier

                        .clip(CircleShape)
                        .clickable { navController.popBackStack() }
                        .padding(8.dp),
                    imageVector = Icons.Default.ArrowBack, contentDescription = "back"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = Movie.getMoviesList().first { it.id == movieID }.title,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back to Home")
            }

        }
    }
}

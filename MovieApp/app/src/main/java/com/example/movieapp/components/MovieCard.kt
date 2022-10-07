package com.example.movieapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie


@Composable
fun MovieCard(movie: Movie, onPressed: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPressed.invoke(movie) },
        shape = RoundedCornerShape(CornerSize(16.dp)),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                    .padding(8.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "movie image"
                )
            }
            Text(text = movie.title, style = MaterialTheme.typography.h4)
        }
    }

}

package com.example.movieapp.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.models.Movie


@Preview
@Composable
fun MovieCard(movie: Movie = Movie.getMoviesList().first(), onPressed: (Movie) -> Unit = {}) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .height(130.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPressed.invoke(movie) },
        shape = RoundedCornerShape(CornerSize(16.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[0])
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "poster",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Release year: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )

                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Text(text = buildAnnotatedString {
                            withStyle(SpanStyle()) {
                                append("Plot: ")
                            }
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(movie.plot)
                            }
                        })
                    }
                }

                // Expand Button
                Icon(
                    modifier = Modifier.clickable { isExpanded = !isExpanded },
                    imageVector = if (!isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "extend"
                )
            }
        }
    }
}

package com.example.testproject

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testproject.ui.theme.TestProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    BizCard()
                }
            }
        }
    }
}

@Composable
fun BizCard() {
    val portfolioButtonState = remember {
        mutableStateOf(value = false);
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        // Here is the UI
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(corner = CornerSize(18.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp,
        ) {

            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ProfileAvatar()
                Divider(
                    color = Color.LightGray,
                    thickness = 1.5.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                InfoSection()
                Button(onClick = {
                    portfolioButtonState.value = !portfolioButtonState.value
                }) {
                    Text(
                        text = "Portfolio", style = MaterialTheme.typography.button
                    )
                }
                if (portfolioButtonState.value) Content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                corner = CornerSize(6.dp)
            ),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }

}

@Composable
fun Portfolio(data: List<String>) {

    LazyColumn {
        items(data) { item: String ->
            ProjectCard(project = item)
        }
    }
}

@Composable
fun ProjectCard(project: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfileAvatar(modifier = Modifier.size(90.dp))
            Box(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = project,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primaryVariant
                )
                Box(modifier = Modifier.height(4.dp))
                Text(
                    text = "This is the second parameter",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.LightGray
                )
            }
        }

    }
}

@Composable
private fun InfoSection() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Alaa Zamel",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Software Engineer", modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@AlaaZamel87",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun ProfileAvatar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(135.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Fit
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestProjectTheme {
        BizCard()
    }
}
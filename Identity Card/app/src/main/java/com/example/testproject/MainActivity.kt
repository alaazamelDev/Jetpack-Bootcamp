package com.example.testproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testproject.ui.theme.TestProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestProjectTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    Surface(
        color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Counter()
    }
}

@Composable
fun Counter() {
    var count by remember {
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\$${count}",
            style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(128.dp))
        CircleButton(count) {
            count++;
        }
    }
}

@Composable
private fun CircleButton(count: Int, onClicked: () -> Unit) {
    var count1 = count
    Surface(
        modifier = Modifier
            .size(200.dp)
            .clickable { onClicked() },
        color = Color.LightGray,
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                textAlign = TextAlign.Center,
                text = "Increment",
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview(name = "App UI", showBackground = true)
@Composable
fun DevicePreview() {
    App()
}
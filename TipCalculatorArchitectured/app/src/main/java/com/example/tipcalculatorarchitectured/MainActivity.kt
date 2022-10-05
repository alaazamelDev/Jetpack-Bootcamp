package com.example.tipcalculatorarchitectured

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorarchitectured.ui.theme.TipCalculatorArchitecturedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopHeader()
                }
            }
        }
    }
}

@Composable
fun MainApp(content: @Composable () -> Unit) {
    TipCalculatorArchitecturedTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp {
        TopHeader()
    }
}

//@Preview(name = "Top Header")
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0XFFE9D7F7)
    ) {
        val total = "%.2f".format(totalPerPerson)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
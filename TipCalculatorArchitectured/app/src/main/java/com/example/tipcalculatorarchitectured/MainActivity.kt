package com.example.tipcalculatorarchitectured

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorarchitectured.components.InputField
import com.example.tipcalculatorarchitectured.ui.theme.TipCalculatorArchitecturedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopHeader()
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainApp(content: @Composable () -> Unit) {
    TipCalculatorArchitecturedTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

//@Preview(name = "Top Header")
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))), color = Color(0XFFE9D7F7)
    ) {
        val total = "%.2f".format(totalPerPerson)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total Per Person", style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent() {
    val totalBillValue = remember {
        mutableStateOf("")
    }

    // to find out if the
    val validFieldState = remember(totalBillValue.value) {
        totalBillValue.value.trim().isNotEmpty()
    }

    // used to control the keyboard
    val keyboardController = LocalSoftwareKeyboardController.current


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(CornerSize(12.dp)))
            .clip(RoundedCornerShape(CornerSize(12.dp)))
            .padding(all = 8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = totalBillValue,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                imeAction = ImeAction.Done,
                onAction = KeyboardActions {
                    if (!validFieldState) return@KeyboardActions

                    // Dismiss the keyboard
                    keyboardController?.hide();
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp {
        Column(modifier = Modifier.fillMaxSize()) {
            TopHeader()
            Spacer(modifier = Modifier.height(16.dp))
            MainContent()
        }
    }
}

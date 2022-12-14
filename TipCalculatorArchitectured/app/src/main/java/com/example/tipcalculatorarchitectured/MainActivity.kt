package com.example.tipcalculatorarchitectured

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.tipcalculatorarchitectured.utils.calculateTipAmount
import com.example.tipcalculatorarchitectured.utils.calculateTotalPerPerson
import com.example.tipcalculatorarchitectured.widgets.RoundedIconButton

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


@Composable
fun MainContent() {
    val totalPerPerson = remember {
        mutableStateOf(0.00)
    }
    Column(
        modifier = Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        TopHeader(totalPerPerson = totalPerPerson.value)
        BillForm() {
            Log.d("total", "MainContent: $it")
            totalPerPerson.value = it.toDouble()
        }

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(onValueChanged: (String) -> Unit = {}) {

    val totalPerPerson = remember {
        mutableStateOf<Double>(0.0)
    }

    val totalBillValue = remember {
        mutableStateOf("")
    }

    // Split value
    val splitValue = remember {
        mutableStateOf(1)
    }

    // to find out if the
    val validFieldState = remember(totalBillValue.value) {
        totalBillValue.value.trim().isNotEmpty()
    }

    // Slider current value
    val sliderValueState = remember {
        mutableStateOf(0f)
    }

    // used to control the keyboard
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
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

                    // invoke the callback to execute based function
                    onValueChanged(totalPerPerson.value.toString())

                    // Dismiss the keyboard
                    keyboardController?.hide();
                },
            )
            if (validFieldState) {
                val sliderIntValue = sliderValueState.value.toInt()

                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Split")
                    Spacer(modifier = Modifier.width(180.dp))
                    RoundedIconButton(imageVector = Icons.Rounded.Remove, onClick = {
                        if (splitValue.value > 1) {
                            splitValue.value--
                            totalPerPerson.value =
                                calculateTotalPerPerson(
                                    totalBill = totalBillValue.value.toDouble(),
                                    splitBy = splitValue.value,
                                    tipPercentage = sliderIntValue
                                )
                            onValueChanged(totalPerPerson.value.toString())
                        }
                    })
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 9.dp)
                            .align(Alignment.CenterVertically), text = "${splitValue.value}"
                    )
                    RoundedIconButton(imageVector = Icons.Rounded.Add, onClick = {
                        splitValue.value++
                        totalPerPerson.value =
                            calculateTotalPerPerson(
                                totalBill = totalBillValue.value.toDouble(),
                                splitBy = splitValue.value,
                                tipPercentage = sliderIntValue
                            )
                        onValueChanged(totalPerPerson.value.toString())
                    })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {


                    Text(text = "Tip")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "%.1f".format(
                            calculateTipAmount(
                                percentage = sliderIntValue,
                                bill = totalBillValue.value.trim().toInt()
                            ).toDouble()
                        )
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))
                // Percentage Text
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    text = "$sliderIntValue%"
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Percentage Slider
                Slider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    value = sliderValueState.value,
                    onValueChange = {
                        sliderValueState.value = it
                        totalPerPerson.value =
                            calculateTotalPerPerson(
                                totalBill = totalBillValue.value.toDouble(),
                                splitBy = splitValue.value,
                                tipPercentage = sliderIntValue
                            )
                        onValueChanged(totalPerPerson.value.toString())
                    },
                    valueRange = 0f..100f,
                    steps = 5,
                )
            }
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

package com.example.jettipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HdrPlus
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettipcalculator.ui.theme.JetTipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}


@Composable
fun MainApp() {

    var totalPerPerson by remember {
        mutableStateOf(0)
    }
    JetTipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ResultBanner(totalPerPerson = totalPerPerson)
                Spacer(Modifier.height(16.dp))
                BillForm() { total ->
                    totalPerPerson = total

                }
            }
        }
    }
}

// Result Banner
@Composable
fun ResultBanner(totalPerPerson: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(CornerSize(10.dp)),
        color = Color(0xFFF1E3D3)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\$ $totalPerPerson",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun BillForm(onChanged: (Int) -> Unit) {
//    var totalPerPerson by remember {
//        mutableStateOf(0)
//    }

    val bill: MutableState<String> = remember {
        mutableStateOf("")
    }

    val numOfContributors: MutableState<Int> = remember {
        mutableStateOf(1)
    }

    var percentageValue by remember {
        mutableStateOf(50f)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                color = Color.LightGray
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = bill.value,
                singleLine = true,
                placeholder = { Text(text = "0") },
                onValueChange = { value ->
                    bill.value = value.toFloat().toInt().toString();
                    onChanged(

                        calcTotalPerPerson(
                            billValue = if (bill.value == "") 0 else bill.value.toFloat().toInt(),
                            numOfCont = numOfContributors.value,
                            tipValue = (((bill.value.toIntOrNull()
                                ?: 0) * percentageValue) / 100).toInt()
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Text(
                        text = "\$", style = MaterialTheme.typography.h6.copy(
                            fontSize = 18.sp, fontWeight = FontWeight.Medium
                        )
                    )
                },
                label = { Text("Enter Bill") })
            Spacer(Modifier.height(16.dp))

            // Number of Contributors
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Label Text
                Text(text = "Split")
                Spacer(modifier = Modifier.weight(1f))

                // Increment Button
                IconButton(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(shape = CircleShape.copy(CornerSize(25.dp))),
                    onClick = {
                        if (numOfContributors.value > 1) {
                            numOfContributors.value--
                            onChanged(

                                calcTotalPerPerson(
                                    billValue = if (bill.value == "") 0 else bill.value.toFloat().toInt(),
                                    numOfCont = numOfContributors.value,
                                    tipValue = (((bill.value.toIntOrNull()
                                        ?: 0) * percentageValue) / 100).toInt()
                                )
                            )
                        }
                    },
                ) {
                    Icon(Icons.Filled.Remove, contentDescription = "dec", tint = Color.Black)
                }

                // Current Value Text
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "${numOfContributors.value}"
                )

                // Decrement Button
                IconButton(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(shape = CircleShape.copy(CornerSize(25.dp))),
                    onClick = {
                        numOfContributors.value++
                        onChanged(

                            calcTotalPerPerson(
                                billValue = if (bill.value == "") 0 else bill.value.toFloat().toInt(),
                                numOfCont = numOfContributors.value,
                                tipValue = (((bill.value.toIntOrNull()
                                    ?: 0) * percentageValue) / 100).toInt()
                            )
                        )
                    },
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "inc", tint = Color.Black)
                }
            }
            Spacer(Modifier.height(16.dp))

            // Tip Value
            TipValue(value = (((bill.value.toIntOrNull() ?: 0) * percentageValue) / 100).toInt())


            Spacer(modifier = Modifier.height(16.dp))

            // Tip Percentage
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${percentageValue.toInt()} %"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Slider(
                value = percentageValue, onValueChange = { newValue ->
                    percentageValue = newValue

                    onChanged(

                        calcTotalPerPerson(
                            billValue = if (bill.value == "") 0 else bill.value.toFloat().toInt(),
                            numOfCont = numOfContributors.value,
                            tipValue = (((bill.value.toIntOrNull()
                                ?: 0) * percentageValue) / 100).toInt()
                        )
                    )


                }, valueRange = 0f..100f, steps = 5
            )

        }
    }
}

@Composable
private fun TipValue(value: Int?) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label Text
        Text(text = "Tip")

        Spacer(modifier = Modifier.weight(1f))

        // Tip Value
        Text(
            modifier = Modifier.padding(end = 32.dp), text = "\$ ${value ?: "0"}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp()
}


private fun calcTotalPerPerson(billValue: Int, numOfCont: Int, tipValue: Int): Int {
    return (billValue + tipValue) / numOfCont
}
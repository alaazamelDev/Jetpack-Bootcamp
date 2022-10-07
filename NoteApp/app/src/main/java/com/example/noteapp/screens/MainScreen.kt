package com.example.noteapp.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar() {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Main Screen",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) {

    }
}
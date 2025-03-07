package com.example.aroundegypt.presentaion.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(openExperienceDetails: (id: Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            {
                openExperienceDetails(1999)
            },
            modifier = Modifier.height(50.dp)
        ) {
            Text("Open Experience Details")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen() {

    }
}
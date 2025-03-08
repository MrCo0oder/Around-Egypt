package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.R

@Composable
fun HomeHeader() {
    Column(
        modifier = Modifier
            .padding(horizontal = 19.dp)
            .padding(top = 11.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.welcome),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            textAlign = TextAlign.Start,
        )
        Text(
            stringResource(R.string.now_you_can_explore_any_experience_in_360_degrees_and_get_all_the_details_about_it_all_in_one_place),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun HomeLabel(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black,
        textAlign = TextAlign.Start, modifier = Modifier
            .padding(19.dp)
            .fillMaxWidth()
    )
}
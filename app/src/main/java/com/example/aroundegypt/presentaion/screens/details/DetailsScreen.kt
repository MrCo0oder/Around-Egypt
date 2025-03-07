package com.example.aroundegypt.presentaion.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, goBack: () -> Unit) {
    val state = rememberModalBottomSheetState(true)
    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = {
            goBack()
        },
        dragHandle = {

        },
        shape = BottomSheetDefaults.ExpandedShape,
        containerColor = BottomSheetDefaults.ContainerColor,
        contentColor = Color.Black,
        tonalElevation = BottomSheetDefaults.Elevation,
        scrimColor = BottomSheetDefaults.ScrimColor,
        properties = ModalBottomSheetProperties(true),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(0.95f)
        ) {
            Text(text = "Details Screen $id")
        }
    }

}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(id = 1) {}
}
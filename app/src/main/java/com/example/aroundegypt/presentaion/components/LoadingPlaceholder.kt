package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.domain.model.Experience
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer

@Composable
fun LoadingPlaceholder() {
    ListingRow(
        {}, Experience(), modifier = Modifier.placeholder(
            visible = true,
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(),
        )
    )
}
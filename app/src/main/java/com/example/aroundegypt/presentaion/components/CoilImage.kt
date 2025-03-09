package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer

@Composable
fun CoilImage(
    imageUrl: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
            .crossfade(true).build(),
        contentDescription = contentDescription,
        loading = {
            Box(
                Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight.shimmer()
                    ), contentAlignment = Alignment.Center
            ) {
            }
        },
        error = {
            Text(
                contentDescription.toString(),
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        },
        contentScale = ContentScale.FillBounds,
        modifier = modifier.fillMaxSize()
    )
}
package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.R

@Composable
fun ViewsRow(
    modifier: Modifier,
    views: String
) {
    Row(
        modifier
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_views),
            contentDescription = "views",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = views,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.ic_pictures),
            contentDescription = "pictures",
            tint = Color.White,
            modifier = modifier
                .padding(10.dp)
                .size(25.dp)
        )
    }
}
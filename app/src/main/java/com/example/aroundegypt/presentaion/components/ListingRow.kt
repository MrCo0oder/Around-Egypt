package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.presentaion.theme.Accent

@Composable
fun ListingRow(
    openExperienceDetails: (id: Int) -> Unit, index: Int, sampleItems: List<String>
) {
    val screenWidth = LocalConfiguration.current.smallestScreenWidthDp.dp
    val itemWidth = screenWidth * 0.95f
    ListItem({
        Box(
            modifier = Modifier

                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(12.dp))
                .wrapContentSize(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            start = Offset.Zero,
                            end = Offset(x = 0f, y = 1000f)
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .background(Color.Black.copy(0.5f), CircleShape)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = "star",
                    tint = Accent
                )
                Text(
                    text = stringResource(R.string.recommended),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 10.sp),
                    color = Color.White,
                )
            }
            Icon(
                Icons.Outlined.Info,
                contentDescription = "info",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp)
                    .align(Alignment.TopEnd)
            )
            Icon(
                painter = painterResource(R.drawable.ic_360),
                contentDescription = "star",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
            )
            Row(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_views),
                    contentDescription = "star",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "22",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_pictures),
                contentDescription = "star",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp)
                    .align(Alignment.BottomEnd)
            )

        }
    },
        modifier = Modifier
            .background(Color.White)
//            .height(250.dp)
            .width(itemWidth)
            .clip(RoundedCornerShape(12.dp))
            .clickable { openExperienceDetails(index) },
        supportingContent = {
            Row(
                Modifier
                    .padding(vertical = 5.dp)
                    .width(itemWidth),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sampleItems[index],
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "22", style = MaterialTheme.typography.bodyMedium, color = Color.Black
                )
                IconButton({
                    //TODO: Add like
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like_outlined),
                        contentDescription = "heart",
                        tint = Accent
                    )
                }
            }
        })
}
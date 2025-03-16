package com.example.aroundegypt.presentaion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.theme.Accent

@Composable
fun ListingRow(
    openExperienceDetails: (id: String) -> Unit,
    experienceItems: Experience,
    onLike: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.smallestScreenWidthDp.dp
    val itemWidth = screenWidth * 0.95f
    val isLiked = rememberSaveable { mutableStateOf(false) }
    ListItem({
        Box(
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(12.dp))
                .wrapContentSize(Alignment.Center),
            contentAlignment = Alignment.Center,
        ) {
            CoilImage(
                imageUrl = experienceItems.coverPhoto,
                contentDescription = experienceItems.title, modifier = modifier
            )
            if (experienceItems != Experience())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                start = Offset(x = 0f, y = 400f),
                                end = Offset(x = 0f, y = 500f)
                            )
                        )
                )
            if (experienceItems.recommended)
                Row(
                    modifier = modifier
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
                contentDescription = "information",
                tint = Color.White,
                modifier = modifier
                    .padding(10.dp)
                    .size(25.dp)
                    .align(Alignment.TopEnd)
            )
            Icon(
                painter = painterResource(R.drawable.ic_360),
                contentDescription = "recommended",
                tint = Color.White,
                modifier = modifier
                    .size(40.dp)
                    .align(Alignment.Center)
            )
            ViewsRow(
                modifier.align(Alignment.BottomCenter),
                experienceItems.viewsNo.toString()
            )
        }
    },
        modifier = Modifier
            .background(Color.White)
            .width(itemWidth)
            .clip(RoundedCornerShape(12.dp))
            .clickable { openExperienceDetails(experienceItems.id) },
        supportingContent = {
            Row(
                Modifier
                    .padding(vertical = 5.dp)
                    .width(itemWidth),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = experienceItems.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = modifier.defaultMinSize(minWidth = 40.dp)
                )
                Spacer(modifier = modifier.weight(1f))
                LikesView(experienceItems, modifier, isLiked, onLike)
            }
        })
}
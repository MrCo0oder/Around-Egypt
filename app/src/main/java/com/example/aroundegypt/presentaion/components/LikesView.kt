package com.example.aroundegypt.presentaion.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.theme.Accent

@Composable
fun LikesView(
    experienceItems: Experience,
    modifier: Modifier,
    isLiked: MutableState<Boolean>,
    onLike: () -> Unit
) {
    val likeState = remember{ mutableStateOf(experienceItems.isLiked) }
    Text(
        text = experienceItems.likesNo.toString(),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black, modifier = modifier
    )
    IconButton(
        {
            isLiked.value = true
            likeState.value = true
            onLike()
        },
        enabled = experienceItems.isLiked.not() || likeState.value.not(),
    ) {
        Icon(
            painter = painterResource(id = if (experienceItems.isLiked) R.drawable.ic_like else R.drawable.ic_like_outlined),
            contentDescription = "heart",
            tint = Accent, modifier = modifier
        )
    }
}
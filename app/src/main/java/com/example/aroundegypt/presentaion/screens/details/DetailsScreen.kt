package com.example.aroundegypt.presentaion.screens.details

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.components.CoilImage
import com.example.aroundegypt.presentaion.components.LoadingPlaceholder
import com.example.aroundegypt.presentaion.components.RetryView
import com.example.aroundegypt.presentaion.components.ViewsRow
import com.example.aroundegypt.presentaion.theme.Accent
import com.example.aroundegypt.utilitis.Resources
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String, detailsViewModel: DetailsViewModel = hiltViewModel(), goBack: () -> Unit
) {
    val state = rememberModalBottomSheetState(true)
    LaunchedEffect(Unit) {
        detailsViewModel.getDetails(id)
    }
    val uiState = detailsViewModel.getExperienceDetails.collectAsStateWithLifecycle().value
    val experience: MutableState<Experience?> = remember { mutableStateOf(null) }



    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = {
            goBack()

        },
        dragHandle = { },
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = BottomSheetDefaults.ContainerColor,
        contentColor = Color.Black,
        tonalElevation = BottomSheetDefaults.Elevation,
        scrimColor = BottomSheetDefaults.ScrimColor,
        properties = ModalBottomSheetProperties(true),
        sheetMaxWidth = Dp.Infinity
    ) {

        when (uiState) {
            is Resources.Success -> {
                experience.value = uiState.data
                val isLiked = rememberSaveable { mutableStateOf(experience.value?.isLiked) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.94f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), contentAlignment = Alignment.Center
                    ) {

                        experience.value?.let {
                            CoilImage(
                                imageUrl = it.coverPhoto,
                                contentDescription = it.title,

                                )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        start = Offset(x = 0f, y = 500f),
                                        end = Offset(x = 0f, y = 900f),
                                        tileMode = androidx.compose.ui.graphics.TileMode.Decal
                                    )
                                )
                        )
                        Button(
                            {

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, contentColor = Accent
                            ),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.explore_now),
                                style = MaterialTheme.typography.bodyLarge,
                                color = Accent
                            )
                        }
                        experience.value?.viewsNo?.let {
                            ViewsRow(
                                Modifier.align(Alignment.BottomCenter),
                                stringResource(R.string.views, it)
                            )
                        }
                    }
                    experience.value?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.5f)
                                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        it.title,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        stringResource(R.string.egypt, it.city),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    val context = LocalContext.current
                                    IconButton(
                                        {
                                            shareText(it.tourHtml, context)
                                        },
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_share),
                                            contentDescription = "share",
                                            tint = Accent,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    IconButton(
                                        {
                                            isLiked.value = isLiked.value?.not()
                                        },
                                        enabled = !isLiked.value!!,
                                    ) {
                                        Icon(
                                            painter = painterResource(id = if (isLiked.value == true) R.drawable.ic_like else R.drawable.ic_like_outlined),
                                            contentDescription = "heart",
                                            tint = Accent,
                                            modifier = Modifier
                                        )
                                    }
                                    Text(
                                        text = it.likesNo.toString(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Black,
                                        modifier = Modifier
                                    )

                                }
                            }
                            Spacer(
                                Modifier
                                    .padding(vertical = 20.dp)
                                    .background(Color(0xFFDADADA))
                                    .height(1.dp)
                                    .fillMaxWidth()


                            )
                            Text(
                                text = stringResource(R.string.description),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = it.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black,
                            )
                        }
                    }
                }
            }

            is Resources.Error -> {
                Box(contentAlignment = Alignment.Center) {
                    RetryView(uiState.message.toString()) {
                        detailsViewModel.getDetails(id)
                    }
                }
            }

            is Resources.Loading -> {
                Box(contentAlignment = Alignment.Center) {
                    LoadingPlaceholder()
                }
            }
        }
    }

}

private fun shareText(
    it: String,
    context: Context
) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, it)
        type = "text/plain"
    }
    try {
        context.startActivity(
            Intent.createChooser(
                shareIntent,
                "Share via"
            )
        )
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "No app available to share",
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(id = "1") {}
}
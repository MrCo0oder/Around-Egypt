package com.example.aroundegypt.presentaion.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.R
import com.example.aroundegypt.presentaion.theme.Accent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, goBack: () -> Unit) {
    val state = rememberModalBottomSheetState(true)
    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = {
            goBack()
        },
        dragHandle = { },
        shape = BottomSheetDefaults.ExpandedShape,
        containerColor = BottomSheetDefaults.ContainerColor,
        contentColor = Color.Black,
        tonalElevation = BottomSheetDefaults.Elevation,
        scrimColor = BottomSheetDefaults.ScrimColor,
        properties = ModalBottomSheetProperties(true),
        sheetMaxWidth = Dp.Infinity
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.94f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
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
                        containerColor = Color.White,
                        contentColor = Accent
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = stringResource(R.string.explore_now),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Accent
                    )
                }

                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
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
                        text = "22 views",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(start = 5.dp)
                    )

                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_pictures),
                        contentDescription = "star",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(25.dp)

                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState()),
            ) { }
        }
    }

}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(id = 1) {}
}
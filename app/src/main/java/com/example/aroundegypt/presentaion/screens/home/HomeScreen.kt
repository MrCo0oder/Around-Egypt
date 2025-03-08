package com.example.aroundegypt.presentaion.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.presentaion.components.AppBar
import com.example.aroundegypt.presentaion.components.HomeHeader
import com.example.aroundegypt.presentaion.components.HomeLabel
import com.example.aroundegypt.presentaion.components.ListingRow
import com.example.aroundegypt.presentaion.theme.Accent
import com.valentinilk.shimmer.shimmer

@Composable
fun HomeScreen(openExperienceDetails: (id: Int) -> Unit) {
    val sampleItems = listOf(
        "Apple",
        "Banana",
        "Cherry",
        "Durian",
        "Elderberry",
        "Fig",
        "Grape",
        "Honeydew",
        "Jackfruit",
        "Kiwi"
    )
    var selectedItem by remember { mutableStateOf("") }
    val nestedScrollConnection = rememberNestedScrollInteropConnection()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(
            items = sampleItems,
            onItemClick = { item ->
                selectedItem = item
                openExperienceDetails(item.hashCode())
            },
            onSearch = { query ->
                println("Searching for: $query")
            })
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .nestedScroll(nestedScrollConnection)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeHeader()
            HomeLabel(stringResource(R.string.recommended_experiences))
            LazyRow {
                items(sampleItems.size) { index ->
                    ListingRow(openExperienceDetails, index, sampleItems)
                }
            }
            HomeLabel(stringResource(R.string.most_recent))
            sampleItems.forEachIndexed { index, _ ->
                ListingRow(openExperienceDetails, index, sampleItems)
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen() {

    }
}
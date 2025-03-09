package com.example.aroundegypt.presentaion.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.components.AppBar
import com.example.aroundegypt.presentaion.components.HomeHeader
import com.example.aroundegypt.presentaion.components.HomeLabel
import com.example.aroundegypt.presentaion.components.ListingRow
import com.example.aroundegypt.presentaion.components.LoadingPlaceholder
import com.example.aroundegypt.presentaion.components.RetryView
import com.example.aroundegypt.presentaion.screens.details.DetailsViewModel
import com.example.aroundegypt.utilitis.Resources

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    openExperienceDetails: (id: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.apply {
            getRecommendedList()
            getMostRecentList()
        }
    }
    val recommendedListState = viewModel.recommendedExperiencesState.collectAsState().value
    val mostRecentListState = viewModel.mostRecentExperiencesState.collectAsState().value
    val filterListState = viewModel.filteredExperiencesState.collectAsState().value
    var selectedItem by remember { mutableStateOf(Experience()) }
    val nestedScrollConnection = rememberNestedScrollInteropConnection()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(
            items = filterListState.data ?: emptyList(),
            onItemClick = { item ->
                selectedItem = item
                openExperienceDetails(item.id)
            },
            onSearch = { query ->
                viewModel.getFilteredList(query)
            }) {
            viewModel.clearSearch()
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .nestedScroll(nestedScrollConnection)
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeHeader()
            HomeLabel(stringResource(R.string.recommended_experiences))
            when (recommendedListState) {
                is Resources.Success -> {
                    if (recommendedListState.data.isNullOrEmpty()) {
                        Text("No data available!")
                    } else {
                        LazyRow {
                            items(recommendedListState.data!!.size) { index ->
                                ListingRow(
                                    openExperienceDetails,
                                    recommendedListState.data!![index], onLike = {
                                        detailsViewModel.likeExperience(recommendedListState.data!![index].id)
                                        viewModel.getRecommendedList()
                                    }
                                )
                            }
                        }
                    }
                }

                is Resources.Error -> {
                    RetryView(
                        recommendedListState.message
                            ?: stringResource(R.string.something_went_wrong)
                    ) {
                        viewModel.getRecommendedList()
                    }
                }

                is Resources.Loading -> {
                    for (i in 0..4)
                        LoadingPlaceholder()
                }
            }
            HomeLabel(stringResource(R.string.most_recent))
            when (mostRecentListState) {
                is Resources.Success -> {
                    if (mostRecentListState.data.isNullOrEmpty()) {
                        Text("No data available!")
                    } else {
                        mostRecentListState.data!!.forEachIndexed { index, _ ->
                            ListingRow(
                                openExperienceDetails,
                                mostRecentListState.data!![index],
                                onLike = {
                                    detailsViewModel.likeExperience(mostRecentListState.data!![index].id)
                                    viewModel.getMostRecentList()
                                })
                        }
                    }
                }

                is Resources.Error -> {
                    RetryView(
                        mostRecentListState.message ?: stringResource(R.string.something_went_wrong)
                    ) {
                        viewModel.getMostRecentList()
                    }
                }

                is Resources.Loading -> {
                    for (i in 0..4)
                        LoadingPlaceholder()
                }
            }
        }
    }
}





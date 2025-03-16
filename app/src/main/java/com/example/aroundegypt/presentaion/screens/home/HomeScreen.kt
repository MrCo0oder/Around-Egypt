package com.example.aroundegypt.presentaion.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.components.AppBar
import com.example.aroundegypt.presentaion.components.HomeLabel
import com.example.aroundegypt.presentaion.components.ListingRow
import com.example.aroundegypt.presentaion.components.LoadingPlaceholder
import com.example.aroundegypt.presentaion.components.RetryView
import com.example.aroundegypt.utilitis.Resources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    openExperienceDetails: (id: String) -> Unit
) {

    val pullRefreshState = rememberPullToRefreshState()


    val recommendedListState =
        viewModel.recommendedExperiencesState.collectAsStateWithLifecycle().value
    val mostRecentListState =
        viewModel.mostRecentExperiencesState.collectAsStateWithLifecycle().value
    val filterListState = viewModel.filteredExperiencesState.collectAsStateWithLifecycle().value
    var selectedItem by remember { mutableStateOf(Experience()) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        if (destination.route == "home") {
            viewModel.apply {
                getRecommendedList()
                getMostRecentList()
            }
        }
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppBar(
            items = filterListState,
            onItemClick = { item ->
                selectedItem = item
                openExperienceDetails(item.id)
            },
            onLike = { item ->
                viewModel.likeExperience(item.id)
            },
            onSearch = { query ->
                if (query.isNotEmpty())
                    viewModel.getFilteredList(query)
            }) {
            viewModel.clearSearch()
        }
        PullToRefreshBox(
            isRefreshing = viewModel.mostRecentExperiencesState.collectAsState().value is Resources.Loading
                    || viewModel.recommendedExperiencesState.collectAsState().value is Resources.Loading,
            state = pullRefreshState,
            onRefresh = {
                viewModel.apply {
                    getRecommendedList()
                    getMostRecentList()
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    HomeLabel(stringResource(R.string.recommended_experiences))
                    when (recommendedListState) {
                        is Resources.Success -> {
                            LazyRow {
                                items(recommendedListState.data!!.size) { index ->
                                    ListingRow(
                                        openExperienceDetails,
                                        recommendedListState.data!![index], onLike = {
                                            viewModel.likeExperience(recommendedListState.data!![index].id)
                                        }
                                    )
                                }
                            }
                        }

                        is Resources.Error -> {
                            RetryView(recommendedListState.message ?: "Something went wrong") {
                                viewModel.getRecommendedList()
                            }
                        }

                        is Resources.Loading -> {
                            LazyRow {
                                items(4) {
                                    LoadingPlaceholder()
                                }
                            }
                        }
                    }
                }
                item {
                    HomeLabel(stringResource(R.string.most_recent))
                }
                when (mostRecentListState) {
                    is Resources.Success -> {
                        if (mostRecentListState.data.isNullOrEmpty()) {
                            item {
                                Text("No data available!")
                            }
                        } else {
                            items(mostRecentListState.data!!.size) { index ->
                                ListingRow(
                                    openExperienceDetails,
                                    mostRecentListState.data!![index],
                                    onLike = {
                                        viewModel.likeExperience(mostRecentListState.data!![index].id)
                                    })

                            }
                        }
                    }

                    is Resources.Error -> {
                        item {
                            RetryView(
                                mostRecentListState.message
                                    ?: stringResource(R.string.something_went_wrong)
                            ) {
                                viewModel.getMostRecentList()
                            }
                        }
                    }

                    is Resources.Loading -> {
                        item(4) {
                            LoadingPlaceholder()
                        }
                    }
                }

            }
        }
    }
}


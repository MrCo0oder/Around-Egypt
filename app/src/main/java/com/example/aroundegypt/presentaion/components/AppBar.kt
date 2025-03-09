package com.example.aroundegypt.presentaion.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentaion.theme.Gray
import com.example.aroundegypt.presentaion.theme.Gray12


@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    hint: String = stringResource(R.string.try_luxor),
    items: List<Experience> = emptyList(),
    onItemClick: (Experience) -> Unit = {},
    onMenuClicked: () -> Unit = {},
    onFilterClicked: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    clearSearch: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    var searchQuery by remember { mutableStateOf("") }
    var isSearchFocused by remember { mutableStateOf(false) }

    BackHandler(enabled = isSearchFocused && searchQuery.isNotEmpty()) {
        focusManager.clearFocus()
    }

    val clearSearchAndDismiss = {
        searchQuery = ""
        focusManager.clearFocus()
        clearSearch()
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .padding(top = 40.dp)
                .background(Color.White),
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onMenuClicked, modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.15f)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }
            OutlinedTextField(
                value = searchQuery,
                textStyle = MaterialTheme.typography.labelLarge.copy(Color.Black),
                onValueChange = { newValue ->
                    searchQuery = newValue
                },
                modifier = Modifier
                    .onFocusChanged {
                        isSearchFocused = it.isFocused
                    }
                    .weight(1f),
                placeholder = {
                    Text(text = hint, style = MaterialTheme.typography.bodySmall, color = Gray)
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(20.dp),
                        tint = Gray
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = clearSearchAndDismiss
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search and dismiss"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(searchQuery)
//                        focusManager.clearFocus()
                    }
                ),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Gray12,
                    unfocusedContainerColor = Gray12,
                    disabledContainerColor = Gray12,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            IconButton(
                onFilterClicked, modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.15f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "filter",
                    tint = Color.Black
                )
            }
        }
        AnimatedVisibility(
            visible = isSearchFocused && searchQuery.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                items(items.size) { item ->
                    ListingRow(
                        openExperienceDetails = {
                            searchQuery = (items[item].title)
                            onItemClick(items[item])
                            focusManager.clearFocus()
                        },
                        experienceItems = items[item],
                        modifier = Modifier
                    )


                }

                if (items.isEmpty() && searchQuery.isNotEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No results found", style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }


}


@Preview(
    showBackground = true
)
@Composable
private fun AppBarPreview() {

}
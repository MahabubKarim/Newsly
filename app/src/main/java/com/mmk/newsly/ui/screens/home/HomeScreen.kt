package com.mmk.newsly.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mmk.newsly.data.model.Article
import com.mmk.newsly.ui.components.ErrorView
import com.mmk.newsly.ui.components.LoadingView
import com.mmk.newsly.ui.components.NewsCard
import com.mmk.newsly.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val newsPagingItems = viewModel.newsPagingFlow.collectAsLazyPagingItems()

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = newsPagingItems.loadState.refresh is LoadState.Loading
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Newsly") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { newsPagingItems.refresh() },
            modifier = Modifier.padding(innerPadding)
        ) {
            when (val state = newsPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    if (newsPagingItems.itemCount == 0) {
                        LoadingView()
                    }
                }

                is LoadState.Error -> {
                    ErrorView(
                        message = state.error.localizedMessage ?: "Unknown error",
                        onRetry = { newsPagingItems.retry() }
                    )
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(newsPagingItems.itemCount) { index ->
                            newsPagingItems[index]?.let { article ->
                                NewsCard(
                                    article = article,
                                    onBookmarkClick = { viewModel.toggleBookmark(article) },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    onArticleClick(article)
                                }
                            }
                        }

                        if (newsPagingItems.loadState.append is LoadState.Loading) {
                            item {
                                LoadingView(modifier = Modifier.fillMaxWidth())
                            }
                        }

                        if (newsPagingItems.loadState.append is LoadState.Error) {
                            item {
                                ErrorView(
                                    message = "Error loading more articles",
                                    onRetry = { newsPagingItems.retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

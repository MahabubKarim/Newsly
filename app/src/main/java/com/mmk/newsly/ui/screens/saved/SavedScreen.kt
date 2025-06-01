package com.mmk.newsly.ui.screens.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmk.newsly.R
import com.mmk.newsly.data.model.Article
import com.mmk.newsly.ui.components.EmptyStateView
import com.mmk.newsly.ui.components.NewsCard
import com.mmk.newsly.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val savedArticles by viewModel.bookmarks.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.saved_articles)) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (savedArticles.isEmpty()) {
                EmptyStateView(
                    message = stringResource(R.string.no_saved_articles),
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(savedArticles) { article ->
                        NewsCard(
                            article = article,
                            onBookmarkClick = { viewModel.toggleBookmark(article) },
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = { onArticleClick(article) }
                        )
                    }
                }
            }
        }
    }
}
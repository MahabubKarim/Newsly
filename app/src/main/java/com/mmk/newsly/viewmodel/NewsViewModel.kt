package com.mmk.newsly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mmk.newsly.data.model.Article
import com.mmk.newsly.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val newsPagingFlow: Flow<PagingData<Article>> = repository
        .getTopHeadlines()
        .cachedIn(viewModelScope)

    val bookmarks: Flow<List<Article>> = repository.getBookmarks()

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            repository.toggleBookmark(article)
        }
    }

    fun getArticle(url: String): Flow<Article?> {
        return viewModelScope.launch {
            repository.getArticle(url)
        } as Flow<Article?>
    }

    fun loadArticle(url: String) {
        viewModelScope.launch {
            repository.loadArticle(url)
        }
    }
}
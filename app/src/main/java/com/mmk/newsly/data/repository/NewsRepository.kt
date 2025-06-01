package com.mmk.newsly.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmk.newsly.data.api.NewsApiService
import com.mmk.newsly.data.local.NewsDatabase
import com.mmk.newsly.data.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val api: NewsApiService,
    private val db: NewsDatabase
) {
    private val articleDao = db.articleDao()

    @OptIn(ExperimentalPagingApi::class)
    fun getTopHeadlines(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NewsRemoteMediator(api, db)
        ) {
            articleDao.pagingSource()
        }.flow
    }

    fun getBookmarks(): Flow<List<Article>> {
        return articleDao.getBookmarks()
    }

    suspend fun toggleBookmark(article: Article) {
        val updated = article.copy(isBookmarked = !article.isBookmarked)
        articleDao.updateArticle(updated)
    }
    suspend fun getArticle(url: String): Flow<Article?> {
        return articleDao.getArticle(url)
    }

    suspend fun loadArticle(url: String) {
        // Implement if you need to fetch from network
    }

}
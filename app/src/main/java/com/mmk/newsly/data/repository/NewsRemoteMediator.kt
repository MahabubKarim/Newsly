package com.mmk.newsly.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mmk.newsly.BuildConfig
import com.mmk.newsly.data.api.NewsApiService
import com.mmk.newsly.data.local.NewsDatabase
import com.mmk.newsly.data.model.Article
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val api: NewsApiService,
    private val db: NewsDatabase
) : RemoteMediator<Int, Article>() {

    private val articleDao = db.articleDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    (lastItem?.publishedAt?.hashCode() ?: 0) + 1
                }
            }

            val response = api.getTopHeadlines(
                apiKey = BuildConfig.NEWS_API_KEY,
                page = page
            )
            val articles = response.articles

            val bookmarkedUrls = articleDao.getBookmarkedUrls()
            val updatedArticles = articles.map { article ->
                article.copy(isBookmarked = article.url in bookmarkedUrls)
            }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearNonBookmarked()
                }
                articleDao.upsertAll(updatedArticles)
            }

            MediatorResult.Success(
                endOfPaginationReached = articles.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
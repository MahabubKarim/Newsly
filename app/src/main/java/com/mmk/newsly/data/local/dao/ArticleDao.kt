package com.mmk.newsly.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.mmk.newsly.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun pagingSource(): PagingSource<Int, Article>

    @Upsert
    suspend fun upsertAll(articles: List<Article>)

    @Update
    suspend fun updateArticle(article: Article)

    @Query("SELECT url FROM articles WHERE isBookmarked = 1")
    suspend fun getBookmarkedUrls(): List<String>

    @Query("DELETE FROM articles WHERE isBookmarked = 0")
    suspend fun clearNonBookmarked()

    @Query("SELECT * FROM articles WHERE url = :url")
    suspend fun getArticle(url: String): Flow<Article?>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1 ORDER BY publishedAt DESC")
    fun getBookmarks(): Flow<List<Article>>
}
package com.mmk.newsly.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?,
    val author: String?,
    val publishedAt: String,
    val urlToImage: String?,
    val content: String?,
    val sourceName: String,
    var isBookmarked: Boolean = false
)
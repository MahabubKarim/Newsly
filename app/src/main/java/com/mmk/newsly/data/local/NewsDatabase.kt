package com.mmk.newsly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmk.newsly.data.local.dao.ArticleDao
import com.mmk.newsly.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
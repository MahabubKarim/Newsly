package com.mmk.newsly.data.api

import com.mmk.newsly.data.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse

    data class NewsResponse(
        val articles: List<Article>,
        val totalResults: Int
    )
}
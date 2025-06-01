package com.mmk.newsly.di

import android.content.Context
import androidx.room.Room
import com.mmk.newsly.data.api.NewsApiService
import com.mmk.newsly.data.local.NewsDatabase
import com.mmk.newsly.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "newsly.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: NewsApiService,
        db: NewsDatabase
    ): NewsRepository {
        return NewsRepository(api, db)
    }
}
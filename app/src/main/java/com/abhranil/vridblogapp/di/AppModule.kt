package com.abhranil.vridblogapp.di

import com.abhranil.vridblogapp.data.remote.BlogApi
import com.abhranil.vridblogapp.data.repo.BlogRepository
import com.abhranil.vridblogapp.data.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api)

    @Singleton
    @Provides
    fun provideBlogApi() : BlogApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlogApi::class.java)
    }
}
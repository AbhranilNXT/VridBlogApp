package com.abhranil.vridblogapp.data.remote

import com.abhranil.vridblogapp.data.model.details.BlogDetails
import com.abhranil.vridblogapp.data.model.main.VridBlogDataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BlogApi {
    @GET(value = "posts")
    suspend fun getBlog(
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): List<VridBlogDataItem>

    @GET("posts/{blogID}")
    suspend fun getBlogDetails(@Path("blogID") blogID: Int) :Response<BlogDetails>
}
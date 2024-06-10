package com.abhranil.vridblogapp.data.remote

import com.abhranil.vridblogapp.data.model.VridBlogData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BlogApi {
    @GET(value = "posts")
    suspend fun getBlog(
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ) : Response<VridBlogData>
}
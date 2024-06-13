package com.abhranil.vridblogapp.data.repo

import com.abhranil.vridblogapp.data.model.details.BlogDetails
import com.abhranil.vridblogapp.data.model.main.VridBlogDataItem
import com.abhranil.vridblogapp.data.remote.BlogApi
import com.abhranil.vridblogapp.data.utils.UiState
import javax.inject.Inject

class BlogRepository @Inject constructor(private val api: BlogApi) {
    suspend fun getBlogs(
        page: Int
    ): List<VridBlogDataItem> = api.getBlog(per_page = "10", page = page.toString())

    suspend fun getBlogDetails(id: Int) : UiState<BlogDetails> {
        val response = api.getBlogDetails(blogID = id)
        if(response.isSuccessful)
            return UiState.Success(data = response.body()!!)
        else return UiState.Error(message = "Error")
    }
}
package com.abhranil.vridblogapp.data.repo

import com.abhranil.vridblogapp.data.model.VridBlogData
import com.abhranil.vridblogapp.data.remote.BlogApi
import com.abhranil.vridblogapp.data.utils.UiState
import javax.inject.Inject

class BlogRepository @Inject constructor(private val api: BlogApi) {
    suspend fun getBlogs(page: String) : UiState<VridBlogData> {
        val response = api.getBlog(per_page = 10.toString(), page = page)
        if(response.isSuccessful)
            return UiState.Success(data = response.body()!!)
        else return UiState.Error(message = "Error")
    }
}
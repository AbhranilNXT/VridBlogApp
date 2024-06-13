package com.abhranil.vridblogapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abhranil.vridblogapp.data.model.main.VridBlogDataItem
import com.abhranil.vridblogapp.data.repo.BlogRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BlogPagingSource @Inject constructor(
    private val repository: BlogRepository
) : PagingSource<Int, VridBlogDataItem>() {

    override fun getRefreshKey(state: PagingState<Int, VridBlogDataItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VridBlogDataItem> {
        val page = params.key ?: 1
        val response = repository.getBlogs(page)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        }catch (e: IOException) {
            LoadResult.Error(
                e
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                e
            )
        }
    }
}
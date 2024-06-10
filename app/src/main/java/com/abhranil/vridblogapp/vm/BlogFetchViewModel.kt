package com.abhranil.vridblogapp.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhranil.vridblogapp.data.model.VridBlogData
import com.abhranil.vridblogapp.data.repo.BlogRepository
import com.abhranil.vridblogapp.data.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogFetchViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {

    private val _blogs: MutableStateFlow<UiState<VridBlogData>> = MutableStateFlow(UiState.Idle)
    val blogs = _blogs.asStateFlow()

    fun loadBlogs(page : String) {
        fetchBlogs(page)
    }

    private fun fetchBlogs(page : String) {
        _blogs.value = UiState.Loading

        viewModelScope.launch {
            try {
                _blogs.value = repository.getBlogs(page = page)
            }
            catch (e: Exception) {
                Log.e("BlogFetchViewModel", e.message.toString())
            }
        }
    }
}
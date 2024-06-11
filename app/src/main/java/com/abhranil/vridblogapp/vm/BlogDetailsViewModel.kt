package com.abhranil.vridblogapp.vm

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhranil.vridblogapp.data.model.main.VridBlogData
import com.abhranil.vridblogapp.data.model.details.BlogDetails
import com.abhranil.vridblogapp.data.repo.BlogRepository
import com.abhranil.vridblogapp.data.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BlogDetailsViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {
    private val _blogInfo : MutableStateFlow<UiState<BlogDetails>> = MutableStateFlow(UiState.Idle)
    val blogInfo = _blogInfo.asStateFlow()

    fun blogDetails(blogID: Int) {
        getBlogDetails(blogID)
    }

    private fun getBlogDetails(blogID: Int) {
        _blogInfo.value = UiState.Loading

        viewModelScope.launch {
            try {
                _blogInfo.value = repository.getBlogDetails(id = blogID)
            }
            catch (e: Exception) {
                Log.d("BlogDetailsViewModel", "getBlogDetails: ${e.message}")
            }
        }
    }
}
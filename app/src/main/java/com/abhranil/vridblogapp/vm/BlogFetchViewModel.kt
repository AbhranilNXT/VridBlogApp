package com.abhranil.vridblogapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.abhranil.vridblogapp.data.model.main.VridBlogDataItem
import com.abhranil.vridblogapp.data.remote.BlogPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogFetchViewModel @Inject constructor(
    private val blogsPagingSource: BlogPagingSource
) : ViewModel() {

    private val _blogResponse: MutableStateFlow<PagingData<VridBlogDataItem>> =
        MutableStateFlow(PagingData.empty())
    var blogResponse = _blogResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                blogsPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _blogResponse.value = it
            }
        }
    }

}
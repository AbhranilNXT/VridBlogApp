package com.abhranil.vridblogapp.view.screens

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abhranil.vridblogapp.data.utils.UiState
import com.abhranil.vridblogapp.vm.BlogFetchViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: BlogFetchViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {

        val blogs = viewModel.blogs.collectAsState().value
        when(blogs)
        {
            is UiState.Idle -> {
                viewModel.loadBlogs(page = 1.toString())
            }
            is UiState.Loading -> {
//                CircularProgressIndicator()
            }
            is UiState.Success -> {
                val listOfBlogs = blogs.data[0].link
                Log.d("BLOG DATA", "blogs: $listOfBlogs")
                WebView(url = listOfBlogs)
            }
            else -> {}
        }
    }
}

@Composable
fun WebView(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(url)
    })
}
package com.abhranil.vridblogapp.view.screens

import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abhranil.vridblogapp.data.utils.UiState
import com.abhranil.vridblogapp.view.components.BlogAppBar
import com.abhranil.vridblogapp.vm.BlogDetailsViewModel

@Composable
fun DetailsScreen(navController: NavController,
                  blogID : Int,
                  viewModel : BlogDetailsViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        BlogAppBar(
            title = "Blog Web View",
            isHomeScreen = false,
            navController = navController) {
            navController.popBackStack()
        }
    }) {paddingValues ->
        Surface(modifier = Modifier.fillMaxSize()
            .padding(paddingValues = paddingValues)) {

            val data = viewModel.blogInfo.collectAsState().value
            when(data)
            {
                is UiState.Idle -> {
                    viewModel.blogDetails(blogID = blogID)
                }
                is UiState.Loading -> {
                    Log.d("Details","Loading")
                }
                is UiState.Success -> {
                    WebView(url = data.data.link)
                }
                else -> {}
            }
        }

    }
}

@Composable
fun WebView(url: String) {
    AndroidView(factory = {
        android.webkit.WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(url)
    })
}
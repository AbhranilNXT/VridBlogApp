package com.abhranil.vridblogapp.view.screens

import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abhranil.vridblogapp.R
import com.abhranil.vridblogapp.data.utils.UiState
import com.abhranil.vridblogapp.view.components.BlogAppBar
import com.abhranil.vridblogapp.vm.BlogDetailsViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DetailsScreen(navController: NavController,
                  blogID : Int,
                  viewModel : BlogDetailsViewModel = hiltViewModel()) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))
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
                    Column(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LottieAnimation(composition = composition,
                            modifier = Modifier.size(128.dp),
                            contentScale = ContentScale.Fit,
                            iterations = LottieConstants.IterateForever)
                    }
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
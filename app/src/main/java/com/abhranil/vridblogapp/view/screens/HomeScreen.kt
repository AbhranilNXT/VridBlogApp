package com.abhranil.vridblogapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.abhranil.vridblogapp.R
import com.abhranil.vridblogapp.view.components.BlogAppBar
import com.abhranil.vridblogapp.view.components.BlogCard
import com.abhranil.vridblogapp.vm.BlogFetchViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun HomeScreen(navController: NavController, viewModel: BlogFetchViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        BlogAppBar(title = "Vrid Blog App",
            isHomeScreen = true,
            navController = navController)
    }) {
        paddingValues ->
        Surface(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                val blogs =viewModel.blogResponse.collectAsLazyPagingItems()
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                    contentPadding = PaddingValues(start = 32.dp, end = 32.dp, bottom = 56.dp)
                ) {
                    items(blogs.itemCount) {
                        BlogCard(blogDataItem = blogs[it]!!, navController = navController)
                    }
                    blogs.apply {
                        when {
                            loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                                item {
                                    Column(
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        LottieAnimation(composition = composition,
                                            modifier = Modifier.size(128.dp),
                                            contentScale = ContentScale.Fit)
                                    }
                                }
                            }

                            loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                                item {
                                    Text(text = "Error")
                                }
                            }

                            loadState.refresh is LoadState.NotLoading -> {
                            }
                        }
                    }
                }

            }
        }
    }
}
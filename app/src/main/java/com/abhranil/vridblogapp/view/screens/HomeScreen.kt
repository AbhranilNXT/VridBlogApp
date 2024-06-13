package com.abhranil.vridblogapp.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abhranil.vridblogapp.data.utils.UiState
import com.abhranil.vridblogapp.view.components.BlogAppBar
import com.abhranil.vridblogapp.view.components.BlogCard
import com.abhranil.vridblogapp.view.navigation.VridBlogScreens
import com.abhranil.vridblogapp.vm.BlogFetchViewModel

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
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                val blogs =viewModel.blogs.collectAsState().value
                var pageNum = 1
                when(blogs)
                {
                    is UiState.Idle -> {
                        viewModel.loadBlogs(page = 1.toString())
                    }
                    is UiState.Loading -> {
//                        CircularProgressIndicator()
                    }
                    is UiState.Success -> {
                        val listOfBlogs = blogs.data
                        LazyColumn(modifier = Modifier.fillMaxSize().weight(1f),
                            contentPadding = PaddingValues(start = 32.dp, end = 32.dp, bottom = 56.dp)
                        ) {
                            items(listOfBlogs.size) {
                                BlogCard(blogDataItem = listOfBlogs[it], navController = navController)
                            }
                        }
                        Row(modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "Next Page ➡️",
                                modifier = Modifier.clickable {
                                    pageNum+=1
                                    navController.navigate(VridBlogScreens.BlogPagesScreen.route+"/$pageNum")
                                }
                            )
                        }
                    }
                    else -> {}

                }

            }
        }
    }
}
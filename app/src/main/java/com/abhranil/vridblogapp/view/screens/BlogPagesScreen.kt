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
fun BlogPagesScreen(navController: NavController,
                    viewModel: BlogFetchViewModel = hiltViewModel(),
                    pageNum: Int = 2) {
    var page = pageNum
    Scaffold(topBar = {
        BlogAppBar(title = "Page $pageNum",
            isHomeScreen = false,
            navController = navController) {
            navController.navigate(VridBlogScreens.HomeScreen.route)
        }
    }) {
            paddingValues ->
        Surface(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                val blogs =viewModel.blogs.collectAsState().value
                when(blogs)
                {
                    is UiState.Idle -> {
                        viewModel.loadBlogs(page = page.toString())
                    }
                    is UiState.Loading -> {
//                        CircularProgressIndicator()
                    }
                    is UiState.Success -> {
                        val listOfBlogs = blogs.data
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                            contentPadding = PaddingValues(start = 32.dp, end = 32.dp, bottom = 56.dp)
                        ) {
                            items(listOfBlogs.size) {
                                BlogCard(blogDataItem = listOfBlogs[it], navController = navController)
                            }
                        }
                        Row(modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            if(page>2){
                                Text(text = "<- Previous Page",
                                    color = Color.White,
                                    modifier = Modifier.clickable {
                                        page-=1
                                        navController.navigate(VridBlogScreens.BlogPagesScreen.route+"/$page")
                                    }
                                )
                            }
                            else {
                                Text(text = "<- Previous Page",
                                    color = Color.White,
                                    modifier = Modifier.clickable {
                                        navController.navigate(VridBlogScreens.HomeScreen.route)
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "Next Page ->",
                                color = Color.White,
                                modifier = Modifier.clickable {
                                    page+=1
                                    navController.navigate(VridBlogScreens.BlogPagesScreen.route+"/$page")
                                }
                            )
                        }
                    }
                    else -> {
                        Text(text = "Something went wrong")
                        Text(text = "Go back to Main Screen",
                            Modifier.clickable {
                                navController.navigate(VridBlogScreens.HomeScreen.route)
                            })
                    }

                }
            }
        }
    }
}
package com.abhranil.vridblogapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhranil.vridblogapp.view.screens.DetailsScreen
import com.abhranil.vridblogapp.view.screens.HomeScreen

@Composable
fun VridBlogNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = VridBlogScreens.HomeScreen.route) {

        composable(VridBlogScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(VridBlogScreens.DetailsScreen.route) {
            DetailsScreen(navController = navController)
        }
    }
}
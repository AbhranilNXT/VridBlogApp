package com.abhranil.vridblogapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun VridBlogNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = VridBlogScreens.HomeScreen.route) {
        
    }
}
package com.abhranil.vridblogapp.view.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhranil.vridblogapp.view.screens.DetailsScreen
import com.abhranil.vridblogapp.view.screens.HomeScreen


@Composable
fun VridBlogNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = VridBlogScreens.HomeScreen.route) {

        composable(VridBlogScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        val detailScreen = VridBlogScreens.DetailsScreen.route
        composable("$detailScreen/{blogID}",
            arguments = listOf(
                navArgument(name = "blogID") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("blogID").let {
                if(it != null) {
                    Log.d("BlogID", it.toString())
                    DetailsScreen(navController = navController, blogID = it)
                }
            }
        }
    }
}
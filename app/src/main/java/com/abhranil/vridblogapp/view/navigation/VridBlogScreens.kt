package com.abhranil.vridblogapp.view.navigation

sealed class VridBlogScreens(val route: String) {
    object HomeScreen : VridBlogScreens("home_screen")
    object DetailsScreen : VridBlogScreens("details_screen")
}
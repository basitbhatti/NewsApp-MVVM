package com.freshapp.newsapp.navigation

sealed class Screen (val route:String) {

    object HomeScreen : Screen("home")
    object DetailScreen : Screen("detail")

}
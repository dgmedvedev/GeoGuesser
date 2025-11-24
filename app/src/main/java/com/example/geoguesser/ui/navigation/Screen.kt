package com.example.geoguesser.ui.navigation

sealed class Screen(val route: String) {
    data object Authorization : Screen("authorization")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Map : Screen("map")
}
package com.example.geoguesser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.geoguesser.ui.screen.authorization.AuthorizationScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Authorization.route,
    ) {
        composable(Screen.Authorization.route) {
            AuthorizationScreen(
                onLoginClick = { navController.navigate(Screen.Home.route) },
                onNavigateBack = { }
            )
        }
        composable(Screen.Home.route) { }
        composable(Screen.Profile.route) { }
        composable(Screen.Map.route) { }
    }
}

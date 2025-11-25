package com.example.geoguesser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.geoguesser.ui.screen.authorization.AuthorizationScreen
import com.example.geoguesser.ui.screen.home.HomeScreen
import com.example.geoguesser.ui.screen.map.MapScreen
import com.example.geoguesser.ui.screen.profile.ProfileScreen

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
        composable(Screen.Home.route) {
            HomeScreen(
                onUserClick = { navController.navigate(Screen.Profile.route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                onGroupClick = { navController.navigate(Screen.Map.route) },
                onNavigateBack = { navController.popBackStack() },
            )
        }
        composable(Screen.Map.route) {
            MapScreen(
                onMapClick = { navController.navigate(Screen.Home.route) },
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}

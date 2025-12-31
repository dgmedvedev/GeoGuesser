package com.example.geoguesser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.geoguesser.ui.screen.authentication.SignInScreen
import com.example.geoguesser.ui.screen.home.HomeScreen
import com.example.geoguesser.ui.screen.map.MapScreen
import com.example.geoguesser.ui.screen.profile.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(
                onSignInClick = { navController.navigate(Screen.Home.route) },
                onNavigateToSignUp = { }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onUserClick = { navController.navigate(Screen.Profile.route) },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                onGroupClick = { navController.navigate(Screen.Map.route) },
                onNavigateBack = { navController.navigateUp() },
            )
        }
        composable(Screen.Map.route) {
            MapScreen(
                onMapClick = { },
                onNavigateBack = { navController.navigateUp() },
            )
        }
    }
}

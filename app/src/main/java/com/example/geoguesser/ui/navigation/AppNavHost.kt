package com.example.geoguesser.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.geoguesser.ui.screen.authentication.SignInScreen
import com.example.geoguesser.ui.screen.authentication.SignUpScreen
import com.example.geoguesser.ui.screen.home.HomeScreen
import com.example.geoguesser.ui.screen.map.MapScreen
import com.example.geoguesser.ui.screen.profile.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.SignIn.route,
        ) {
            composable(Screen.SignIn.route) {
                SignInScreen(
                    onSignInClick = { navController.navigate(Screen.Home.route) },
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUp.route) {
                            popUpTo(Screen.SignIn.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.SignUp.route) {
                SignUpScreen(
                    onSignUpClick = { navController.navigate(Screen.Home.route) },
                    onNavigateToSignIn = {
                        navController.navigate(Screen.SignIn.route) {
                            popUpTo(Screen.SignUp.route) { inclusive = true }
                        }
                    }
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
}

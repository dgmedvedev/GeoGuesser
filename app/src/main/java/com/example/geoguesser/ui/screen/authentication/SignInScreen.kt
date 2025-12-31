package com.example.geoguesser.ui.screen.authentication

import androidx.compose.runtime.Composable

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {

    SignInContent(
        onSignInClick = onSignInClick,
        onNavigateToSignUp = onNavigateToSignUp,
    )
}
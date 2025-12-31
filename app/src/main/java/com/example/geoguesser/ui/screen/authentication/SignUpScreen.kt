package com.example.geoguesser.ui.screen.authentication

import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {

    SignUpContent(
        onSignUpClick = onSignUpClick,
        onNavigateToSignIn = onNavigateToSignIn,
    )
}

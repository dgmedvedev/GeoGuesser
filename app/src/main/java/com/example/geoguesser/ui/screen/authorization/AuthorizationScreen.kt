package com.example.geoguesser.ui.screen.authorization

import androidx.compose.runtime.Composable

@Composable
fun AuthorizationScreen(
    onLoginClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {

    AuthorizationContent(
        onLoginClick = onLoginClick,
        onNavigateBack = onNavigateBack,
    )
}
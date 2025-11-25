package com.example.geoguesser.ui.screen.home

import androidx.compose.runtime.Composable

@Composable
internal fun HomeScreen(
    onUserClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    HomeContent(
        onUserClick = onUserClick,
        onNavigateBack = onNavigateBack,
    )
}
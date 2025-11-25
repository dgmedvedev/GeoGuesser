package com.example.geoguesser.ui.screen.profile

import androidx.compose.runtime.Composable

@Composable
internal fun ProfileScreen(
    onGroupClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    ProfileContent(
        onGroupClick = onGroupClick,
        onNavigateBack = onNavigateBack,
    )
}
package com.example.geoguesser.ui.screen.map

import androidx.compose.runtime.Composable

@Composable
internal fun MapScreen(
    onMapClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    MapContent(
        onMapClick = onMapClick,
        onNavigateBack = onNavigateBack,
    )
}
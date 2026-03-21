package com.example.geoguesser.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.geoguesser.R

@Composable
fun ErrorText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = integerResource(R.integer.max_error_lines),
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

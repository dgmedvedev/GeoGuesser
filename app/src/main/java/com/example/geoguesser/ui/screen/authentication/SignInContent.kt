package com.example.geoguesser.ui.screen.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.geoguesser.R
import com.example.geoguesser.ui.components.InputField

@Composable
fun SignInContent(
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = dimensionResource(id = R.dimen.spacing_screen_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.spacing_top_to_title),
                    bottom = dimensionResource(id = R.dimen.spacing_title_to_subtitle)
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clickable { }
                    .padding(end = dimensionResource(R.dimen.spacing_between_titles))
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in_title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = dimensionResource(R.dimen.divider_thickness)
                )
            }
            Text(
                text = stringResource(id = R.string.sign_up_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable(onClick = onNavigateToSignUp)
            )
        }

        Text(
            text = stringResource(R.string.sign_in_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_subtitle_to_input))
        )

        InputField(
            value = username,
            onValueChange = { username = it },
            labelResId = R.string.username_label
        )

        Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.spacing_between_inputs)))

        InputField(
            value = password,
            onValueChange = { password = it },
            labelResId = R.string.password_label,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = dimensionResource(id = R.dimen.button_height_large)),
            shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.corner_radius_small)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_button_to_hint)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.no_account_text) + " ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = onNavigateToSignUp)
            )
        }
    }
}

@Preview
@Composable
fun SignInContentPreview() {
    MaterialTheme {
        SignInContent(
            onSignInClick = {},
            onNavigateToSignUp = {}
        )
    }
}

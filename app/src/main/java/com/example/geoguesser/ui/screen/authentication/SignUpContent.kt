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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.geoguesser.R
import com.example.geoguesser.domain.model.AppError
import com.example.geoguesser.ui.components.ErrorText
import com.example.geoguesser.ui.components.InputField

@Composable
fun SignUpContent(
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
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
            Text(
                text = stringResource(id = R.string.sign_in_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.spacing_between_titles))
                    .clickable(onClick = onNavigateToSignIn)
            )
            Column(
                modifier = Modifier
                    .clickable { }
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = dimensionResource(R.dimen.divider_thickness)
                )
            }
        }

        Text(
            text = stringResource(R.string.sign_up_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_subtitle_to_input))
        )

        InputField(
            value = uiState.username,
            onValueChange = onUsernameChange,
            labelResId = R.string.username_label
        )

        Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.spacing_between_inputs)))

        InputField(
            value = uiState.email,
            onValueChange = onEmailChange,
            labelResId = R.string.email_label
        )

        Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.spacing_between_inputs)))

        InputField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            labelResId = R.string.password_label,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.spacing_between_inputs)))

        InputField(
            value = uiState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            labelResId = R.string.confirm_password_label,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.weight(1f))

        uiState.error?.let { error ->

            val message = when (error) {
                is AppError.Unauthorized -> "Login failed"
                is AppError.InvalidResponse -> error.reason
                is AppError.Server -> "Server error (code ${error.code}): ${error.message}"
                is AppError.Network -> "Network error: ${error.message}"
                is AppError.Validation -> error.message
                is AppError.Unknown -> "Unexpected error: ${error.message}"
            }
            ErrorText(
                text = message,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_button_to_hint))
            )
        }

        Button(
            onClick = onSignUpClick,
            enabled = !uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = dimensionResource(id = R.dimen.button_height_large)),
            shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.corner_radius_small)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_button_to_hint)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.already_have_account_text) + " ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = onNavigateToSignIn)
            )
        }
    }
}

@Preview
@Composable
fun SignUpContentPreview() {
    MaterialTheme {
        SignUpContent(
            uiState = SignUpUiState(error = AppError.Validation("All fields are required")),
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onSignUpClick = {},
            onNavigateToSignIn = {}
        )
    }
}

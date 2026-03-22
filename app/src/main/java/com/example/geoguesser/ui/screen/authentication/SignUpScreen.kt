package com.example.geoguesser.ui.screen.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SignUpContent(
        uiState = uiState,
        onUsernameChange = viewModel::updateUsername,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onConfirmPasswordChange = viewModel::updateConfirmPassword,
        onSignUpClick = { viewModel.signUp(onSuccess = onSignUpClick) },
        onNavigateToSignIn = onNavigateToSignIn,
    )
}

package com.example.geoguesser.ui.screen.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geoguesser.GeoGuesserApp

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    val context = LocalContext.current
    val app = context.applicationContext as GeoGuesserApp
    val viewModel: SignUpViewModel = viewModel(
        factory = SignUpViewModelFactory(app.container.authRepository)
    )
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

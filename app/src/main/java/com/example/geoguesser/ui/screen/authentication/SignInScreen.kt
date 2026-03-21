package com.example.geoguesser.ui.screen.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geoguesser.GeoGuesserApp

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    val context = LocalContext.current
    val app = context.applicationContext as GeoGuesserApp
    val viewModel: SignInViewModel = viewModel(
        factory = SignInViewModelFactory(app.container.authRepository)
    )
    val uiState by viewModel.uiState.collectAsState()

    SignInContent(
        uiState = uiState,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = { viewModel.signIn(onSuccess = onSignInClick) },
        onNavigateToSignUp = onNavigateToSignUp,
    )
}

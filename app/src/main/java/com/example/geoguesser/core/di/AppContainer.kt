package com.example.geoguesser.core.di

import android.content.Context
import com.example.geoguesser.data.local.auth.AuthTokenStorage
import com.example.geoguesser.data.remote.network.NetworkModule
import com.example.geoguesser.data.repository.AuthRepositoryImpl
import com.example.geoguesser.domain.repository.AuthRepository

class AppContainer(context: Context) {
    private val tokenStorage = AuthTokenStorage(context.applicationContext)
    private val networkModule = NetworkModule(tokenStorage)

    val authRepository: AuthRepository = AuthRepositoryImpl(
        authApi = networkModule.authApi,
        tokenStorage = tokenStorage,
    )
}

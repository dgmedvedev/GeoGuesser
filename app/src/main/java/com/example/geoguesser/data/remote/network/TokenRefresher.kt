package com.example.geoguesser.data.remote.network

import com.example.geoguesser.data.local.auth.TokenStorage
import com.example.geoguesser.data.remote.api.AuthApi
import com.example.geoguesser.data.remote.dto.RefreshTokenRequestDto
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Named

class TokenRefresher @Inject constructor(
    @param:Named("refreshAuthApi")
    private val authApi: AuthApi,
    private val tokenStorage: TokenStorage,
) {
    fun refreshToken(): String? {
        val refreshToken = tokenStorage.getRefreshToken()
        if (refreshToken.isNullOrBlank()) {
            tokenStorage.clear()
            return null
        }

        val response = runBlocking {
            authApi.refreshToken(RefreshTokenRequestDto(refreshToken = refreshToken))
        }
        if (!response.isSuccessful) {
            tokenStorage.clear()
            return null
        }

        val body = response.body()
        val newAccessToken = body?.accessToken
        val newRefreshToken = body?.refreshToken
        if (newAccessToken.isNullOrBlank() || newRefreshToken.isNullOrBlank()) {
            tokenStorage.clear()
            return null
        }

        tokenStorage.setAccessToken(newAccessToken)
        tokenStorage.setRefreshToken(newRefreshToken)
        return newAccessToken
    }
}

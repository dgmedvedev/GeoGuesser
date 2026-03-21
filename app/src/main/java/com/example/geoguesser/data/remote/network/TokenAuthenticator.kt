package com.example.geoguesser.data.remote.network

import com.example.geoguesser.data.local.auth.AuthTokenStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenStorage: AuthTokenStorage,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) return null

        val currentToken = tokenStorage.getAccessToken() ?: return null
        val requestToken = response.request.header("Authorization")

        return if (requestToken == "Bearer $currentToken") {
            tokenStorage.clear()
            null
        } else {
            response.request.newBuilder()
                .header("Authorization", "Bearer $currentToken")
                .build()
        }
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var current = response.priorResponse
        while (current != null) {
            result++
            current = current.priorResponse
        }
        return result
    }
}

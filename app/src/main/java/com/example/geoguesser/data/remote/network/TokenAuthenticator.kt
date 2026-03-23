package com.example.geoguesser.data.remote.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenRefresher: TokenRefresher
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) return null
        if (response.request.url.encodedPath.endsWith("/auth/refresh")) return null

        synchronized(this) {
            val newToken = tokenRefresher.refreshToken()

            return newToken?.let {
                response.request.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
            }
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

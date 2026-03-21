package com.example.geoguesser.data.remote.network

import com.example.geoguesser.data.local.auth.AuthTokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenStorage: AuthTokenStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenStorage.getAccessToken()
        val newRequest = chain.request().let { originalRequest ->
            if (token.isNullOrBlank()) originalRequest
            else originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }
        return chain.proceed(newRequest)
    }
}

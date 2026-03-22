package com.example.geoguesser.data.remote.network

import com.example.geoguesser.data.local.auth.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {
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

package com.example.geoguesser.data.remote.network

import com.example.geoguesser.data.local.auth.TokenStorage
import com.example.geoguesser.data.remote.api.AuthApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkModule @Inject constructor(
    tokenStorage: TokenStorage
) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenStorage))
        .addInterceptor(loggingInterceptor)
        .authenticator(TokenAuthenticator(tokenStorage))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8189/demo/"
    }
}

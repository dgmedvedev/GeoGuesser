package com.example.geoguesser.data.remote.api

import com.example.geoguesser.data.remote.dto.AuthRequestDto
import com.example.geoguesser.data.remote.dto.AuthResponseDto
import com.example.geoguesser.data.remote.dto.RefreshTokenRequestDto
import com.example.geoguesser.data.remote.dto.RegistrationRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun signIn(@Body request: AuthRequestDto): Response<AuthResponseDto>

    @POST("auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequestDto): Response<AuthResponseDto>

    @POST("auth/register")
    suspend fun signUp(@Body request: RegistrationRequestDto): Response<Unit>
}

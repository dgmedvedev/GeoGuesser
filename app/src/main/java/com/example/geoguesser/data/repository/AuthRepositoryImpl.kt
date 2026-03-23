package com.example.geoguesser.data.repository

import com.example.geoguesser.data.local.auth.TokenStorage
import com.example.geoguesser.data.remote.api.AuthApi
import com.example.geoguesser.data.remote.dto.AuthRequestDto
import com.example.geoguesser.data.remote.dto.RegistrationRequestDto
import com.example.geoguesser.domain.model.AppError
import com.example.geoguesser.domain.model.Outcome
import com.example.geoguesser.domain.repository.AuthRepository
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStorage: TokenStorage,
) : AuthRepository {

    override suspend fun login(username: String, password: String): Outcome<String> {
        return try {
            val request = AuthRequestDto(username = username, password = password)
            val response = authApi.signIn(request = request)
            if (!response.isSuccessful) {
                when (val code = response.code()) {
                    401 -> Outcome.Failure(AppError.Unauthorized)
                    else -> Outcome.Failure(AppError.Server(code, response.message()))
                }
            } else {
                val tokens = response.body()
                val accessToken = tokens?.accessToken
                val refreshToken = tokens?.refreshToken
                if (accessToken.isNullOrBlank() || refreshToken.isNullOrBlank()) {
                    Outcome.Failure(AppError.InvalidResponse("Tokens are empty"))
                } else {
                    tokenStorage.setAccessToken(accessToken)
                    tokenStorage.setRefreshToken(refreshToken)
                    Outcome.Success(accessToken)
                }
            }
        } catch (e: IOException) {
            Outcome.Failure(AppError.Network(e.message))
        } catch (e: Exception) {
            Outcome.Failure(AppError.Unknown(e.message))
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Outcome<String> {
        return try {
            val request = RegistrationRequestDto(
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                email = email,
            )
            val response = authApi.signUp(request = request)
            if (!response.isSuccessful) {
                when (val code = response.code()) {
                    in 400..499 -> Outcome.Failure(AppError.Server(code, "Registration failed"))
                    else -> Outcome.Failure(AppError.Server(code, response.message()))
                }
            } else return login(username, password)
        } catch (e: IOException) {
            Outcome.Failure(AppError.Network(e.message))
        } catch (e: Exception) {
            Outcome.Failure(AppError.Unknown(e.message))
        }
    }

    override suspend fun logout(): Outcome<Unit> = try {
        tokenStorage.clear()
        Outcome.Success(Unit)
    } catch (e: IOException) {
        Outcome.Failure(AppError.Network(e.message))
    } catch (e: Exception) {
        Outcome.Failure(AppError.Unknown(e.message))
    }

    override suspend fun isUserLoggedIn(): Boolean = !tokenStorage.getAccessToken().isNullOrBlank()
}

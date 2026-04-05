package com.example.geoguesser.data.local.auth

interface TokenStorage {
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun setAccessToken(token: String)
    fun setRefreshToken(token: String)
    fun clear()
}

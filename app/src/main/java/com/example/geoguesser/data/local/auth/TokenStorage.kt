package com.example.geoguesser.data.local.auth

interface TokenStorage {
    fun getAccessToken(): String?
    fun setAccessToken(token: String)
    fun clear()
}

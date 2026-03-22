package com.example.geoguesser.core.di

import com.example.geoguesser.data.local.auth.SharedPrefsTokenStorage
import com.example.geoguesser.data.local.auth.TokenStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenStorageModule {

    @Binds
    @Singleton
    abstract fun bindTokenStorage(impl: SharedPrefsTokenStorage): TokenStorage
}

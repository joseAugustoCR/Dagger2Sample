package com.example.daggersample.di.auth

import aioria.com.br.kotlinbaseapp.networking.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @Module
    companion object {

        @AuthScope
        @JvmStatic
        @Provides
        fun provideApi(retrofit: Retrofit): AuthApi {
           return retrofit.create(AuthApi::class.java)
        }

    }

}
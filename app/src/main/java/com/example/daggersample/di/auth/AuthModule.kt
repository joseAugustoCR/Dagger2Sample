package com.example.daggersample.di.auth

import aioria.com.br.kotlinbaseapp.networking.EndpointsInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@SuppressWarnings("unchecked")
@Module
class AuthModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideApi(retrofit: Retrofit): EndpointsInterface {
           return retrofit.create(EndpointsInterface::class.java)
        }

    }

}
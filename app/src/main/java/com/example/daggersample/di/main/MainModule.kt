package com.example.daggersample.di.main

import aioria.com.br.kotlinbaseapp.networking.AuthApi
import aioria.com.br.kotlinbaseapp.networking.MainApi
import com.example.daggersample.ui.main.posts.PostAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Module
    companion object {


        @JvmStatic
        @Provides
        fun provideAdapter(): PostAdapter {
            return PostAdapter()
        }

        @JvmStatic
        @Provides
        fun provideApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

    }
}
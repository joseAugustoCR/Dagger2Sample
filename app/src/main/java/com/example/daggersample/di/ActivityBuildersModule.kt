package com.example.daggersample.di

import com.example.daggersample.di.auth.AuthModule
import com.example.daggersample.di.auth.AuthViewModelsModule
import com.example.daggersample.ui.auth.AuthActivity
import com.example.daggersample.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = arrayOf(AuthViewModelsModule::class, AuthModule::class)
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity

    // Kotlin doesn't have static methods so we need to wrap in a companion object and annotate with @JvmStatic
    @Module
    companion object {

//        @JvmStatic
//        @Provides //declare a string dependency
//        fun doSomething(): String {
//            return "Doing something..."
//        }


    }
}
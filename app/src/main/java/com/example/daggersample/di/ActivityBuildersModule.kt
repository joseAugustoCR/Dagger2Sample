package com.example.daggersample.di

import com.example.daggersample.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity


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
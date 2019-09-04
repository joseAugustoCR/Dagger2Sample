package com.example.daggersample.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    // If you don't need to do any manipulations inside the method body, you can call @Binds instead of  a static @Provider fun that
    // returns the object
    @Binds
    abstract fun bindViewModelFactory(factory:ViewModelProviderFactory) : ViewModelProvider.Factory
}
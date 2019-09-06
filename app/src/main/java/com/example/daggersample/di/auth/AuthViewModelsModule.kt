package com.example.daggersample.di.auth

import androidx.lifecycle.ViewModel
import com.example.daggersample.di.ViewModelKey
import com.example.daggersample.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel:AuthViewModel) : ViewModel


}
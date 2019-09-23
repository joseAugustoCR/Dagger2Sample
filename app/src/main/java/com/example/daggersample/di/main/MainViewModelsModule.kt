package com.example.daggersample.di.main

import androidx.lifecycle.ViewModel
import com.example.daggersample.di.ViewModelKey
import com.example.daggersample.ui.main.posts.PostsViewModel
import com.example.daggersample.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostsViewModel) : ViewModel

}
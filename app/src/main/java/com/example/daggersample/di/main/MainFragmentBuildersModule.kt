package com.example.daggersample.di.main

import com.example.daggersample.ui.main.posts.PostsFragment
import com.example.daggersample.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment() : PostsFragment
}
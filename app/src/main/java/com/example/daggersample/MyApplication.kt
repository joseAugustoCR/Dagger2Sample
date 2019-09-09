package com.example.daggersample
import android.app.Application
import android.content.Context
import com.example.daggersample.di.DaggerAppComponent
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Scope


@SuppressWarnings("unchecked")
class MyApplication: DaggerApplication() {
    @SuppressWarnings("unchecked")
    override fun applicationInjector(): AndroidInjector<MyApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
    }




}
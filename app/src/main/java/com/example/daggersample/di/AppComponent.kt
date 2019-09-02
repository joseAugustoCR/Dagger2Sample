package com.example.daggersample.di

import android.app.Application
import com.example.daggersample.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = arrayOf(AndroidSupportInjectionModule::class, ActivityBuildersModule::class, AppModule::class)
)
public interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }
    
}
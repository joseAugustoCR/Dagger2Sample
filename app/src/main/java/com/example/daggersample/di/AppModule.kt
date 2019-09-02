package com.example.daggersample.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.daggersample.R
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions().placeholder(R.color.colorPrimary).error(R.color.colorPrimaryDark)
        }


        @JvmStatic
        @Provides
        fun provideRequestManager(application: Application, requestOptions: RequestOptions): RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)
        }

        @JvmStatic
        @Provides
        fun provideAppDrawable(application:Application) : Drawable{
            return ContextCompat.getDrawable(application.applicationContext, R.mipmap.ic_launcher)!!
        }


    }

}